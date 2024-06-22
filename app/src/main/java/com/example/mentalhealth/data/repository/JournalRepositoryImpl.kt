package com.example.mentalhealth.data.repository

import com.example.mentalhealth.data.datasource.FirestoreDataSource
import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.model.MLInput
import com.example.mentalhealth.domain.model.MLOutput
import com.example.mentalhealth.domain.repository.JournalRepository
import org.tensorflow.lite.Interpreter
import javax.inject.Inject

class JournalRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource,
    private val mlInterpreter: Interpreter,
) : JournalRepository {
    override suspend fun getJournalEntry(date: String): DailyJournal? {
        return dataSource.getJournalEntry(date)
    }

    override suspend fun addMorningJournalEntry(
        journalData: DailyJournal,
        date: String
    ): Result<Unit> {
        return dataSource.addMorningJournalEntry(journalData, date)
    }

    override suspend fun addEveningJournalEntry(
        journalData: DailyJournal,
        date: String
    ): Result<Unit> {
        return dataSource.addEveningJournalEntry(journalData, date)
    }

    override suspend fun machineLearningPrediction(
        mlInput: MLInput,
        date: String
    ): Result<Unit> {
        return try {
            val inputData = prepareInput(mlInput)

            val moodOutputArray = Array(1) { FloatArray(33) }
            val recommendation1OutputArray = Array(1) { FloatArray(60) }
            val recommendation2OutputArray = Array(1) { FloatArray(60) }
            val recommendation3OutputArray = Array(1) { FloatArray(60) }
            val recommendation4OutputArray = Array(1) { FloatArray(60) }

            val outputMap = mapOf(
                0 to recommendation1OutputArray,
                1 to recommendation2OutputArray,
                2 to moodOutputArray,
                3 to recommendation3OutputArray,
                4 to recommendation4OutputArray
            )

            mlInterpreter.runForMultipleInputsOutputs(arrayOf(inputData), outputMap)

            val moodPrediction = moodOutputArray[0]
            val recommendation1 = recommendation1OutputArray[0]
            val recommendation2 = recommendation2OutputArray[0]
            val recommendation3 = recommendation3OutputArray[0]
            val recommendation4 = recommendation4OutputArray[0]

            val predictedMoodIndex = moodPrediction.argmax()
            val predictedRecommendation1Index = recommendation1.argmax()
            val predictedRecommendation2Index = recommendation2.argmax()
            val predictedRecommendation3Index = recommendation3.argmax()
            val predictedRecommendation4Index = recommendation4.argmax()

            dataSource.addMLOutput(
                MLOutput(
                    predictedMoodIndex,
                    predictedRecommendation1Index,
                    predictedRecommendation2Index,
                    predictedRecommendation3Index,
                    predictedRecommendation4Index,
                ),
                date
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun prepareInput(mlInput: MLInput): FloatArray {
        return floatArrayOf(
            mlInput.age.toFloat(),
            mlInput.gender.toFloat(),
            mlInput.studies.toFloat(),
            mlInput.occupation.toFloat(),
            mlInput.maritalStatus.toFloat(),
            mlInput.livingArea.toFloat(),
            mlInput.publicFigure.toFloat(),
            mlInput.wakeUpTime.toFloat(),
            mlInput.hoursSlept.toFloat(),
            mlInput.goalsProgress.toFloat(),
            //mlInput.todayIAmGratefulFor.toFloat(),
            mlInput.todayIFelt.toFloat(),
            mlInput.stressLevel.toFloat(),
            mlInput.waterIntake.toFloat(),
            mlInput.energyLevel.toFloat(),
            mlInput.loveLevel.toFloat(),
            mlInput.enoughWater.toFloat(),
            mlInput.enoughFood.toFloat(),
            mlInput.enoughFreshAir.toFloat(),
            mlInput.enoughExercise.toFloat(),
            mlInput.enoughSleep.toFloat(),
            mlInput.enoughRest.toFloat(),
            //mlInput.whatWentWell.toFloat(),
            //mlInput.whatWentBad.toFloat(),
            mlInput.whatDidIDoToTakeCareOfMyself.toFloat(),
            //mlInput.bestMomentOfTheDay.toFloat(),
            mlInput.dayRating.toFloat(),
            mlInput.dayFeeling.toFloat(),
            //mlInput.whatCanIDoToMakeTomorrowBetter.toFloat()
        )
    }

    private fun FloatArray.argmax(): Int {
        return this.indices.maxByOrNull { this[it] } ?: -1
    }
}