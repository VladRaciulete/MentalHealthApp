package com.example.mentalhealth.domain.model

data class MLOutput(
    var moodPrediction: Int = 0,
    var recommendation1: Int = 0,
    var recommendation2: Int = 0,
    var recommendation3: Int = 0,
    var recommendation4: Int = 0,
)
