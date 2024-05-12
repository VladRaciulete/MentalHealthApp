package com.example.mentalhealth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.mentalhealth.ui.theme.BackgroundColor

@Composable
fun StatisticsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Text(
            text = "Statistics Screen!",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
        BottomMenu(
            items = listOf(
                BottomMenuItem("Home", R.drawable.ic_home),
                BottomMenuItem("Home", R.drawable.ic_home),
                BottomMenuItem("Home", R.drawable.ic_home),
                BottomMenuItem("Home", R.drawable.ic_home)
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}