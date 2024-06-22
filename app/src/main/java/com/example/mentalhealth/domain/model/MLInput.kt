package com.example.mentalhealth.domain.model

data class MLInput(
    var age: Int = 0,
    var gender: Int = 0,
    var studies: Int = 0,
    var occupation: Int = 0,
    var maritalStatus: Int = 0,
    var livingArea: Int = 0,
    var publicFigure: Int = 0,

    var wakeUpTime: Int = 0,
    var hoursSlept: Double = 0.0,
    var goalsProgress: Int = 0,
    //var todayIAmGratefulFor: Int = 0,
    var todayIFelt: Int = 0,
    var stressLevel: Int = 0,
    var waterIntake: Int = 0,
    var energyLevel: Int = 0,
    var loveLevel: Int = 0,
    var enoughWater: Int = 0,
    var enoughFood: Int = 0,
    var enoughFreshAir: Int = 0,
    var enoughExercise: Int = 0,
    var enoughSleep: Int = 0,
    var enoughRest: Int = 0,
    //var whatWentWell: Int = 0,
    //var whatWentBad: Int = 0,
    var whatDidIDoToTakeCareOfMyself: Int = 0,
    //var bestMomentOfTheDay: Int = 0,
    var dayRating: Int = 0,
    var dayFeeling: Int = 0,
    //var whatCanIDoToMakeTomorrowBetter: Int = 0
)
