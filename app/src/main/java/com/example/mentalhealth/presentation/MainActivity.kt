package com.example.mentalhealth.presentation

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mentalhealth.presentation.navigation.Navigation
import com.example.mentalhealth.ui.theme.BackgroundColor
import com.example.mentalhealth.ui.theme.MentalHealthTheme
import com.example.mentalhealth.utils.SuccessEvent
import com.example.mentalhealth.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            MentalHealthTheme {
                val appContext = LocalContext.current
                val appStateViewModel: AppStateViewModel = hiltViewModel()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundColor)
                ){
                    Navigation()
                }
                when (appStateViewModel.uiState.value) {
                    is UiState.Idle -> {
                    }

                    is UiState.Loading -> {
                        CustomProgressIndicator()
                    }

                    is UiState.Success -> {
                        val successMessage = (appStateViewModel.uiState.value as UiState.Success).message

                        when(successMessage){
                            SuccessEvent.SUCCESS -> {
                                //Toast.makeText(appContext, SuccessEvent.SUCCESS, Toast.LENGTH_SHORT).show()
                            }
                            SuccessEvent.USER_ACCOUNT_CREATED -> {
                                Toast.makeText(appContext, SuccessEvent.USER_ACCOUNT_CREATED, Toast.LENGTH_SHORT).show()
                            }
                            SuccessEvent.USER_DATA_LOADED -> {
                                //Toast.makeText(appContext, SuccessEvent.USER_DATA_LOADED, Toast.LENGTH_SHORT).show()
                            }
                            SuccessEvent.JOURNAL_ENTRY_ADDED -> {
                                Toast.makeText(appContext, SuccessEvent.JOURNAL_ENTRY_ADDED, Toast.LENGTH_SHORT).show()
                            }
                        }
                        appStateViewModel.uiState.value = UiState.Idle
                    }

                    is UiState.Error -> {
                        val errorMessage = (appStateViewModel.uiState.value as UiState.Error).message
                        println("ERROR $errorMessage")
                    }
                }
            }
        }
    }
}