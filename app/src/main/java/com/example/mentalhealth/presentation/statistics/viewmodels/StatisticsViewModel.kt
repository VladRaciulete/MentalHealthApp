package com.example.mentalhealth.presentation.statistics.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.usecase.statistics.GetJournalEntriesFromDateRangeUseCase
import com.example.mentalhealth.presentation.AppStateViewModel
import com.example.mentalhealth.utils.Constants
import com.example.mentalhealth.utils.ErrorEvent
import com.example.mentalhealth.utils.SuccessEvent
import com.example.mentalhealth.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    val getJournalEntriesFromDateRangeUseCase: GetJournalEntriesFromDateRangeUseCase,
    val appStateViewModel: AppStateViewModel
) : ViewModel() {
    var statisticsTimeRange = mutableStateOf("Last Week")
    private var journalEntriesList: List<DailyJournal> = emptyList()

    var averageWakeUpTime = mutableStateOf("0.00")
    var averageHoursSlept = mutableStateOf("0.00")
    var averageDayRating = mutableStateOf("0.00")
    var averageGoalProgress = mutableStateOf("0.00")
    var dayMoods = mutableMapOf<String, Int>()
    var averageStressLevel= mutableStateOf("0.00")
    var averageWaterIntake = mutableStateOf("0.00")
    var averageEnergyLevel = mutableStateOf("0.00")
    var averageLoveLevel = mutableStateOf("0.00")
    var dayFeelings = mutableMapOf<String, Int>()
    var careOfYourself = mutableMapOf<String, Int>()

    private fun getJournalEntries(startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                resetViewModelFields()

                appStateViewModel.uiState.value = UiState.Loading

                val journalEntries = getJournalEntriesFromDateRangeUseCase(startDate, endDate)

                if (journalEntries != null) {
                    journalEntriesList = journalEntries

                    calculateStatistics()

                    appStateViewModel.uiState.value =
                        UiState.Success(SuccessEvent.JOURNAL_ENTRIES_LOADED)
                } else {
                    appStateViewModel.uiState.value =
                        UiState.Error(ErrorEvent.ERROR_RETRIEVING_JOURNAL_ENTRIES)
                }
            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.ERROR_RETRIEVING_JOURNAL_ENTRIES)
            }
        }
    }

    fun loadStatistics() {
        val days = Constants.statisticsTimeRangeMap.getOrElse(statisticsTimeRange.value) { 7 }

        val endDate = LocalDate.now().format(DateTimeFormatter.ofPattern(Constants.APP_DATE_FORMAT))
        val startDate = getStartDate(endDate, days)

        println("days: $days")
        println("startDate: $startDate")
        println("endDate: $endDate")

        getJournalEntries(startDate, endDate)
    }

    private fun getStartDate(endDate: String, days: Int): String{
        val dateFormat = SimpleDateFormat(Constants.APP_DATE_FORMAT, Locale.getDefault())
        val givenDate = dateFormat.parse(endDate)

        if (givenDate != null){
            val calendar = Calendar.getInstance()
            calendar.time = givenDate

            calendar.add(Calendar.DAY_OF_YEAR, -days)

            val resultingDate = calendar.time
            val startDate = dateFormat.format(resultingDate)
            return startDate
        }

        return endDate
    }

    private fun calculateStatistics() {
        var entriesCounter = 0

        var wakeUpTimeSum = 0
        var hoursSleptSum = 0.0
        var dayRatingSum = 0
        var stressLevelSum = 0
        var waterIntakeSum = 0
        var energyLevelSum = 0
        var loveLevelSum = 0

        var goalsCounter = 0
        var goalsProgress = 0


        for (entry in journalEntriesList) {
            entriesCounter += 1

            val split = entry.wakeUpTime.split(":")
            val hours = split[0].toInt()
            val minutes = split[1].toInt()
            wakeUpTimeSum += hours * 60 + minutes

            hoursSleptSum += entry.hoursSlept.toDouble()
            dayRatingSum += entry.dayRating
            dayMoods[entry.todayIFelt] = dayMoods.getOrElse(entry.todayIFelt) { 0 } + 1
            stressLevelSum += entry.stressLevel
            waterIntakeSum += entry.waterIntake
            energyLevelSum += entry.energyLevel
            loveLevelSum += entry.loveLevel
            dayFeelings[entry.dayFeeling] = dayFeelings.getOrElse(entry.dayFeeling) { 0 } + 1
            careOfYourself[entry.whatDidIDoToTakeCareOfMyself] = careOfYourself.getOrElse(entry.whatDidIDoToTakeCareOfMyself) { 0 } + 1

            for (goal in entry.dailyGoals) {
                goalsCounter += 1
                goalsProgress += goal.progress
            }
        }

        wakeUpTimeSum /= entriesCounter
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val hours = wakeUpTimeSum / 60
        val minutes = wakeUpTimeSum % 60
        val averageTime = LocalTime.of(hours, minutes)

        averageWakeUpTime.value = averageTime.format(formatter)
        averageHoursSlept.value = formatDoubleAsString(hoursSleptSum/entriesCounter.toDouble())
        averageDayRating.value = formatDoubleAsString(dayRatingSum/entriesCounter.toDouble())
        averageGoalProgress.value = formatDoubleAsString(goalsProgress.toDouble() / goalsCounter.toDouble())
        averageStressLevel.value = formatDoubleAsString(stressLevelSum/entriesCounter.toDouble())
        averageWaterIntake.value = formatDoubleAsString(waterIntakeSum/entriesCounter.toDouble())
        averageEnergyLevel.value = formatDoubleAsString(energyLevelSum/entriesCounter.toDouble())
        averageLoveLevel.value = formatDoubleAsString(loveLevelSum/entriesCounter.toDouble())
    }

    fun resetViewModelFields() {
        journalEntriesList = emptyList()
        averageWakeUpTime.value = "0.00"
        averageHoursSlept.value = "0.00"
        averageDayRating.value = "0.00"
        averageGoalProgress.value = "0.00"
        dayMoods = mutableMapOf()
        averageStressLevel.value = "0.00"
        averageWaterIntake.value = "0.00"
        averageEnergyLevel.value = "0.00"
        averageLoveLevel.value = "0.00"
        dayFeelings = mutableMapOf()
        careOfYourself = mutableMapOf()
    }

    private fun formatDoubleAsString(number: Double): String {
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(number)
    }
}