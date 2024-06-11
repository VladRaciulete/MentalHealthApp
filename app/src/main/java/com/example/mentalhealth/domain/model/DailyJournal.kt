package com.example.mentalhealth.domain.model

data class DailyJournal(
    var morningCheckIn: Boolean = false,
    var eveningCheckIn: Boolean = false,
    var wakeUpTime: String = "",
    var hoursSlept: String = "",
    var dailyGoals: List<Goal> = emptyList(),
    var todayIAmGratefulFor: String = "",
    var todayIFelt: String = "",
    var stressLevel: Int = 0,
    var waterIntake: Int = 0,
    var energyLevel: Int = 0,
    var loveLevel: Int = 0,
    var didIHaveEnough: Map<String,Boolean> = mapOf(
        "Water" to false,
        "Food" to false,
        "Fresh air" to false,
        "Exercise" to false,
        "Sleep" to false,
        "Rest" to false,
    ),
    var whatWentWell: String = "",
    var whatWentBad: String = "",
    var whatDidIDoToTakeCareOfMyself: String = "",
    var bestMomentOfTheDay: String = "",
    var dayRating: Int = 0,
    var dayFeeling: String = "",
    var whatCanIDoToMakeTomorrowBetter: String = "",
    var timeStamp: String = ""
)







