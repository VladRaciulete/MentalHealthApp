package com.example.mentalhealth.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mentalhealth.presentation.navigation.Navigation
import com.example.mentalhealth.ui.theme.MentalHealthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MentalHealthTheme {
                Navigation()
            }
        }
    }
}