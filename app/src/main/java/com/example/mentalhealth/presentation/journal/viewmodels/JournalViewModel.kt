package com.example.mentalhealth.presentation.journal.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.model.Goal
import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.usecase.journal.AddEveningJournalEntryUseCase
import com.example.mentalhealth.domain.usecase.journal.AddMorningJournalEntryUseCase
import com.example.mentalhealth.domain.usecase.journal.GetJournalEntryUseCase
import com.example.mentalhealth.domain.usecase.journal.MLPredictionUseCase
import com.example.mentalhealth.domain.usecase.profile.LoadUserDataUseCase
import com.example.mentalhealth.ml.mapper.MLInputMapper
import com.example.mentalhealth.presentation.AppStateViewModel
import com.example.mentalhealth.utils.ErrorEvent
import com.example.mentalhealth.utils.SuccessEvent
import com.example.mentalhealth.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    val addMorningJournalEntryUseCase: AddMorningJournalEntryUseCase,
    val addEveningJournalEntryUseCase: AddEveningJournalEntryUseCase,
    val getJournalEntryUseCase: GetJournalEntryUseCase,
    val mlPredictionUseCase: MLPredictionUseCase,
    val loadUserDataUseCase: LoadUserDataUseCase,
    val appStateViewModel: AppStateViewModel,
) : ViewModel() {
    var wakeUpTime = mutableStateOf("")
    var hoursSlept = mutableStateOf("")
    var dailyGoals = mutableStateOf<List<Goal>>(emptyList())
    var todayIAmGratefulFor = mutableStateOf("")
    var todayIFelt = mutableStateOf("")
    var stressLevel = mutableStateOf(0)
    var waterIntake = mutableStateOf(0)
    var energyLevel = mutableStateOf(0)
    var loveLevel = mutableStateOf(0)
    var didIHaveEnough = mutableStateOf(
        mapOf(
            "Water" to false,
            "Food" to false,
            "Fresh air" to false,
            "Exercise" to false,
            "Sleep" to false,
            "Rest" to false,
        )
    )
    var whatWentWell = mutableStateOf("")
    var whatWentBad = mutableStateOf("")
    var whatDidIDoToTakeCareOfMyself = mutableStateOf("")
    var bestMomentOfTheDay = mutableStateOf("")
    var dayRating = mutableStateOf(0)
    var dayFeeling = mutableStateOf("")
    var whatCanIDoToMakeTomorrowBetter = mutableStateOf("")
    var timeStamp = mutableStateOf(System.currentTimeMillis())
    var date = mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
    var morningCheckIn = mutableStateOf(false)
    var eveningCheckIn = mutableStateOf(false)

    init {
        getJournalEntry()
    }

    fun getJournalEntry() {
        viewModelScope.launch {
            try {
                resetViewModelFields()
                appStateViewModel.uiState.value = UiState.Loading

                val entry = getJournalEntryUseCase(date.value)

                if (entry != null) {
                    morningCheckIn.value = entry.morningCheckIn
                    eveningCheckIn.value = entry.eveningCheckIn
                    wakeUpTime.value = entry.wakeUpTime
                    hoursSlept.value = entry.hoursSlept
                    dailyGoals.value = entry.dailyGoals
                    todayIAmGratefulFor.value = entry.todayIAmGratefulFor
                    todayIFelt.value = entry.todayIFelt
                    stressLevel.value = entry.stressLevel
                    waterIntake.value = entry.waterIntake
                    energyLevel.value = entry.energyLevel
                    loveLevel.value = entry.loveLevel
                    didIHaveEnough.value = entry.didIHaveEnough
                    whatWentWell.value = entry.whatWentWell
                    whatWentBad.value = entry.whatWentBad
                    whatDidIDoToTakeCareOfMyself.value = entry.whatDidIDoToTakeCareOfMyself
                    bestMomentOfTheDay.value = entry.bestMomentOfTheDay
                    dayRating.value = entry.dayRating
                    dayFeeling.value = entry.dayFeeling
                    whatCanIDoToMakeTomorrowBetter.value = entry.whatCanIDoToMakeTomorrowBetter

                    appStateViewModel.uiState.value =
                        UiState.Success(SuccessEvent.JOURNAL_DATA_LOADED)
                } else {
                    appStateViewModel.uiState.value =
                        UiState.Error(ErrorEvent.ERROR_RETRIEVING_JOURNAL_DATA)
                }
            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.ERROR_RETRIEVING_JOURNAL_DATA)
            }
        }
    }

    fun addMorningJournalCheckIn() {
        viewModelScope.launch {
            try {
                appStateViewModel.uiState.value = UiState.Loading

                val result = addMorningJournalEntryUseCase(
                    DailyJournal(
                        morningCheckIn = true,
                        eveningCheckIn = false,
                        wakeUpTime = wakeUpTime.value,
                        hoursSlept = hoursSlept.value,
                        dailyGoals = dailyGoals.value,
                        todayIAmGratefulFor = todayIAmGratefulFor.value,
                        todayIFelt = todayIFelt.value,
                        stressLevel = stressLevel.value,
                        waterIntake = waterIntake.value,
                        energyLevel = energyLevel.value,
                        loveLevel = loveLevel.value,
                        didIHaveEnough = didIHaveEnough.value,
                        whatWentWell = whatWentWell.value,
                        whatWentBad = whatWentBad.value,
                        whatDidIDoToTakeCareOfMyself = whatDidIDoToTakeCareOfMyself.value,
                        bestMomentOfTheDay = bestMomentOfTheDay.value,
                        dayRating = dayRating.value,
                        dayFeeling = dayFeeling.value,
                        whatCanIDoToMakeTomorrowBetter = whatCanIDoToMakeTomorrowBetter.value,
                    ),
                    date = date.value
                )

                appStateViewModel.uiState.value =
                    if (result.isSuccess)
                        UiState.Success(SuccessEvent.JOURNAL_DATA_ADDED)
                    else
                        UiState.Error(
                            result.exceptionOrNull()?.message
                                ?: ErrorEvent.ERROR_ADDING_JOURNAL_DATA
                        )

                getJournalEntry()

            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.ERROR_ADDING_JOURNAL_DATA)
            }
        }
    }

    fun addEveningJournalCheckIn() {
        viewModelScope.launch {
            try {
                appStateViewModel.uiState.value = UiState.Loading

                val result = addEveningJournalEntryUseCase(
                    DailyJournal(
                        morningCheckIn = morningCheckIn.value,
                        eveningCheckIn = true,
                        wakeUpTime = wakeUpTime.value,
                        hoursSlept = hoursSlept.value,
                        dailyGoals = dailyGoals.value,
                        todayIAmGratefulFor = todayIAmGratefulFor.value,
                        todayIFelt = todayIFelt.value,
                        stressLevel = stressLevel.value,
                        waterIntake = waterIntake.value,
                        energyLevel = energyLevel.value,
                        loveLevel = loveLevel.value,
                        didIHaveEnough = didIHaveEnough.value,
                        whatWentWell = whatWentWell.value,
                        whatWentBad = whatWentBad.value,
                        whatDidIDoToTakeCareOfMyself = whatDidIDoToTakeCareOfMyself.value,
                        bestMomentOfTheDay = bestMomentOfTheDay.value,
                        dayRating = dayRating.value,
                        dayFeeling = dayFeeling.value,
                        whatCanIDoToMakeTomorrowBetter = whatCanIDoToMakeTomorrowBetter.value,
                    ),
                    date = date.value
                )

                appStateViewModel.uiState.value =
                    if (result.isSuccess)
                        UiState.Success(SuccessEvent.JOURNAL_DATA_ADDED)
                    else
                        UiState.Error(
                            result.exceptionOrNull()?.message
                                ?: ErrorEvent.ERROR_ADDING_JOURNAL_DATA
                        )

                getJournalEntry()
                makeMLPrediction()

            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.ERROR_ADDING_JOURNAL_DATA)
            }
        }
    }

    private fun makeMLPrediction() {
        viewModelScope.launch {
            try {
                val loadedUser = loadUserDataUseCase()
                val user: User

                if (loadedUser != null) {
                    user = loadedUser
                } else {
                    user = User()
                }
                val mapper = MLInputMapper()

                val result = mlPredictionUseCase(
                    mapper.map(
                        DailyJournal(
                            morningCheckIn = morningCheckIn.value,
                            eveningCheckIn = true,
                            wakeUpTime = wakeUpTime.value,
                            hoursSlept = hoursSlept.value,
                            dailyGoals = dailyGoals.value,
                            todayIAmGratefulFor = todayIAmGratefulFor.value,
                            todayIFelt = todayIFelt.value,
                            stressLevel = stressLevel.value,
                            waterIntake = waterIntake.value,
                            energyLevel = energyLevel.value,
                            loveLevel = loveLevel.value,
                            didIHaveEnough = didIHaveEnough.value,
                            whatWentWell = whatWentWell.value,
                            whatWentBad = whatWentBad.value,
                            whatDidIDoToTakeCareOfMyself = whatDidIDoToTakeCareOfMyself.value,
                            bestMomentOfTheDay = bestMomentOfTheDay.value,
                            dayRating = dayRating.value,
                            dayFeeling = dayFeeling.value,
                            whatCanIDoToMakeTomorrowBetter = whatCanIDoToMakeTomorrowBetter.value,
                        ),
                        user
                    ),
                    date.value
                )

                appStateViewModel.uiState.value =
                    if (result.isSuccess)
                        UiState.Success(SuccessEvent.ML_DATA_ADDED)
                    else
                        UiState.Error(
                            result.exceptionOrNull()?.message ?: ErrorEvent.ERROR_ADDING_ML_DATA
                        )

            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.ERROR_ADDING_ML_DATA)
            }
        }
    }

    fun resetViewModelFields() {
        morningCheckIn.value = false
        eveningCheckIn.value = false
        wakeUpTime.value = ""
        hoursSlept.value = ""
        dailyGoals.value = emptyList()
        todayIAmGratefulFor.value = ""
        todayIFelt.value = ""
        stressLevel.value = 0
        waterIntake.value = 0
        energyLevel.value = 0
        loveLevel.value = 0
        didIHaveEnough.value = mapOf(
            "Water" to false,
            "Food" to false,
            "Fresh air" to false,
            "Exercise" to false,
            "Sleep" to false,
            "Rest" to false,
        )
        whatWentWell.value = ""
        whatWentBad.value = ""
        whatDidIDoToTakeCareOfMyself.value = ""
        bestMomentOfTheDay.value = ""
        dayRating.value = 0
        dayFeeling.value = ""
        whatCanIDoToMakeTomorrowBetter.value = ""
        timeStamp.value = System.currentTimeMillis()
    }

    fun resetViewModelDate() {
        date.value = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    }

    fun printAllData() {
        resetViewModelFields()
        getJournalEntry()
        println("=================================================")
        println("morningCheckIn : ${morningCheckIn.value}")
        println("eveningCheckIn : ${eveningCheckIn.value}")
        println("wakeUpTime : ${wakeUpTime.value}")
        println("hoursSlept : ${hoursSlept.value}")
        println("dailyGoals : ${dailyGoals.value}")
        println("todayIAmGratefulFor : ${todayIAmGratefulFor.value}")
        println("todayIFelt : ${todayIFelt.value}")
        println("stressLevel : ${stressLevel.value}")
        println("waterIntake : ${waterIntake.value}")
        println("energyLevel : ${energyLevel.value}")
        println("loveLevel : ${loveLevel.value}")
        println("didIHaveEnough : ${didIHaveEnough.value}")
        println("whatWentWell : ${whatWentWell.value}")
        println("whatWentBad : ${whatWentBad.value}")
        println("whatDidIDoToTakeCareOfMyself : ${whatDidIDoToTakeCareOfMyself.value}")
        println("bestMomentOfTheDay : ${bestMomentOfTheDay.value}")
        println("dayRating : ${dayRating.value}")
        println("dayFeeling : ${dayFeeling.value}")
        println("whatCanIDoToMakeTomorrowBetter : ${whatCanIDoToMakeTomorrowBetter.value}")
        println("timeStamp : ${timeStamp.value}")
        println("date : ${date.value}")
        println("=================================================")
    }

    fun formatDate(inputDate: String): Date {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(inputDate) ?: Date()
    }

    fun formatDateString(date: Date): String {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date)
    }

    fun incrementDate() {
        val calendar = Calendar.getInstance()
        calendar.time = formatDate(date.value)
        calendar.add(Calendar.DAY_OF_YEAR, 1) // Increment by one day
        date.value = formatDateString(calendar.time)
    }

    fun decrementDate() {
        val calendar = Calendar.getInstance()
        calendar.time = formatDate(date.value)
        calendar.add(Calendar.DAY_OF_YEAR, -1) // Decrement by one day
        date.value = formatDateString(calendar.time)
    }
}