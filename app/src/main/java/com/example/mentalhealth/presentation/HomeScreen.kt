package com.example.mentalhealth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.bottomMenu.BottomMenu
import com.example.mentalhealth.bottomMenu.BottomMenuItem
import com.example.mentalhealth.R
import com.example.mentalhealth.di.AppModule
import com.example.mentalhealth.navigation.Screen
import com.example.mentalhealth.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    val currentUser = AppModule.provideFirebaseAuthInstance().currentUser
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            Text(
                text = "Home Screen!",
                fontSize = 24.sp,
                color = TextWhiteColor
            )
            Text(
                text = "${currentUser?.email}",
                fontSize = 20.sp,
                color = TextWhiteColor
            )
        }
        BottomMenu(
            navController = navController,
            items = listOf(
                BottomMenuItem("Home", R.drawable.ic_home, Screen.HomeScreen.route),
                BottomMenuItem("Statistics", R.drawable.ic_stats, Screen.StatisticsScreen.route),
                BottomMenuItem("Recommendations", R.drawable.ic_recommended, Screen.RecommendationsScreen.route),
                BottomMenuItem("Account", R.drawable.ic_account, Screen.AccountScreen.route)
            ),
            initialSelectedItemIndex = 0,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}