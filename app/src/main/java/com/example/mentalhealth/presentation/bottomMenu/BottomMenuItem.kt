package com.example.mentalhealth.presentation.bottomMenu

import androidx.annotation.DrawableRes

data class BottomMenuItem(
    val title:String,
    @DrawableRes val iconId: Int,
    val route: String
)
