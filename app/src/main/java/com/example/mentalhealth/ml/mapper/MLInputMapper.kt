package com.example.mentalhealth.ml.mapper

import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.model.Goal
import com.example.mentalhealth.domain.model.MLInput
import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.utils.Constants
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class MLInputMapper {
    fun map(journal: DailyJournal, user: User): MLInput {
        return MLInput(
            age =  mapBirthDate(user.birthDate),
            gender = mapList(user.gender, Constants.genderList),
            studies = mapList(user.studies, Constants.studiesList),
            occupation = mapList(user.occupation, Constants.occupationsList),
            maritalStatus = mapList(user.maritalStatus, Constants.maritalStatusList),
            livingArea = mapList(user.livingArea, Constants.livingAreaList),
            publicFigure = mapList(user.publicFigure, Constants.publicFigureList),

            wakeUpTime = mapWakeUpTime(journal.wakeUpTime),
            hoursSlept = mapHoursSlept(journal.hoursSlept),
            goalsProgress = mapGoalsProgress(journal.dailyGoals),
            //todayIAmGratefulFor = 0,
            todayIFelt = mapList(journal.todayIFelt, Constants.todayFeelings),
            stressLevel = journal.stressLevel,
            waterIntake = journal.waterIntake,
            energyLevel = journal.energyLevel,
            loveLevel = journal.loveLevel,
            enoughWater = mapBoolean(journal.didIHaveEnough["Water"]),
            enoughFood = mapBoolean(journal.didIHaveEnough["Food"]),
            enoughFreshAir = mapBoolean(journal.didIHaveEnough["Fresh air"]),
            enoughExercise = mapBoolean(journal.didIHaveEnough["Exercise"]),
            enoughSleep = mapBoolean(journal.didIHaveEnough["Sleep"]),
            enoughRest = mapBoolean(journal.didIHaveEnough["Rest"]),
            //whatWentWell = 0,
            //whatWentBad = 0,
            whatDidIDoToTakeCareOfMyself = mapList(journal.whatDidIDoToTakeCareOfMyself, Constants.careOfMyself),
            //bestMomentOfTheDay = 0,
            dayRating = journal.dayRating,
            dayFeeling = mapList(journal.dayFeeling, Constants.careOfMyself),
            //whatCanIDoToMakeTomorrowBetter = 0
        )
    }

    private fun mapBirthDate(dateString: String): Int {
        val formatter = DateTimeFormatter.ofPattern(Constants.APP_DATE_FORMAT)
        val birthDate = LocalDate.parse(dateString, formatter)
        val currentDate = LocalDate.now()

        return Period.between(birthDate, currentDate).years
    }

    private fun mapList(element: Any, list: List<*>): Int {
        return list.indexOf(element)
    }

    private fun mapWakeUpTime(time: String): Int {
        val split = time.split(":")
        val hours = split[0].toInt()
        val minutes = split[1].toInt()

        return hours * 60 + minutes
    }

    private fun mapHoursSlept(hoursSlept: String): Double {
        return hoursSlept.toDouble()
    }

    private fun mapGoalsProgress(goals: List<Goal>): Int {
        var averageGoalProgress = 0
        var numberOfGoals = 0

        for (goal in goals) {
            numberOfGoals += 1
            averageGoalProgress += goal.progress
        }

        averageGoalProgress /= numberOfGoals

        return averageGoalProgress
    }

    private fun mapBoolean(value: Boolean?): Int {
        return if (value == true) 1 else 0
    }
}
