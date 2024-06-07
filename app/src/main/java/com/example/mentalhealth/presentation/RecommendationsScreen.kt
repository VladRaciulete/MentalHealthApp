package com.example.mentalhealth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.presentation.bottomMenu.BottomMenu
import com.example.mentalhealth.ui.theme.BackgroundColor
import com.example.mentalhealth.utils.Constants

@Composable
fun RecommendationsScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Text(
            text = "Recommendations Screen!",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
        BottomMenu(
            navController = navController,
            items = Constants.bottomMenuElementList,
            initialSelectedItemIndex = 2,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}