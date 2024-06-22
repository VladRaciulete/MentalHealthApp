package com.example.mentalhealth.presentation.recommendations.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.domain.usecase.recommendations.GetMLOutputUseCase
import com.example.mentalhealth.presentation.AppStateViewModel
import com.example.mentalhealth.utils.Constants
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
class RecommendationsViewModel @Inject constructor(
    val getMLOutputUseCase: GetMLOutputUseCase,
    val appStateViewModel: AppStateViewModel
) : ViewModel() {
    var date = mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
    var moodPrediction = mutableStateOf("")
    var recommendation1 = mutableStateOf("")
    var recommendation2 = mutableStateOf("")
    var recommendation3 = mutableStateOf("")
    var recommendation4 = mutableStateOf("")

    init {
        getMLOutput()
    }

    fun getMLOutput() {
        viewModelScope.launch {
            try {
                resetViewModelFields()
                appStateViewModel.uiState.value = UiState.Loading

                val mlOutput = getMLOutputUseCase(date.value)

                if (mlOutput != null) {
                    moodPrediction.value = Constants.todayFeelings[mlOutput.moodPrediction]
                    recommendation1.value =
                        Constants.personalizedRecommendations[mlOutput.recommendation1]
                    recommendation2.value =
                        Constants.personalizedRecommendations[mlOutput.recommendation2]
                    recommendation3.value =
                        Constants.personalizedRecommendations[mlOutput.recommendation3]
                    recommendation4.value =
                        Constants.personalizedRecommendations[mlOutput.recommendation4]

                    appStateViewModel.uiState.value = UiState.Success(SuccessEvent.ML_DATA_LOADED)
                } else {
                    appStateViewModel.uiState.value =
                        UiState.Error(ErrorEvent.ERROR_RETRIEVING_ML_DATA)
                }
            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.ERROR_RETRIEVING_ML_DATA)
            }
        }
    }

    fun resetViewModelFields() {
        moodPrediction.value = ""
        recommendation1.value = ""
        recommendation2.value = ""
        recommendation3.value = ""
        recommendation4.value = ""
    }

    fun resetViewModelDate() {
        date.value = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
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