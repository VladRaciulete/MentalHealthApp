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
import com.example.mentalhealth.di.AppModule
import com.example.mentalhealth.presentation.bottomMenu.BottomMenu
import com.example.mentalhealth.ui.theme.*
import com.example.mentalhealth.utils.Constants

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
            items = Constants.bottomMenuElementList,
            initialSelectedItemIndex = 0,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}