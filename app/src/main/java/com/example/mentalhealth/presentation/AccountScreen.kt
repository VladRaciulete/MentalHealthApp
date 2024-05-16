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
import com.example.mentalhealth.bottomMenu.BottomMenu
import com.example.mentalhealth.bottomMenu.BottomMenuItem
import com.example.mentalhealth.R
import com.example.mentalhealth.navigation.Screen
import com.example.mentalhealth.ui.theme.BackgroundColor

@Composable
fun AccountScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Text(
            text = "Account Screen!",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
        BottomMenu(
            navController = navController,
            items = listOf(
                BottomMenuItem("Home", R.drawable.ic_home, Screen.HomeScreen.route),
                BottomMenuItem("Statistics", R.drawable.ic_stats, Screen.StatisticsScreen.route),
                BottomMenuItem("Recommendations", R.drawable.ic_recommended, Screen.RecommendationsScreen.route),
                BottomMenuItem("Account", R.drawable.ic_account, Screen.AccountScreen.route)
            ),
            initialSelectedItemIndex = 3,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}