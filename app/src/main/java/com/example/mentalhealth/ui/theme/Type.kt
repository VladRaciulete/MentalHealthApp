package com.example.mentalhealth.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = TextWhiteColor
    )
)

val UnderlinedLinkTextStyle = TextStyle(
    color = TextWhiteColor,
    fontSize = 16.sp,
    textDecoration = TextDecoration.Underline
)