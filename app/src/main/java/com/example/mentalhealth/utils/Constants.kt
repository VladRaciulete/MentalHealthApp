package com.example.mentalhealth.utils

import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.bottomMenu.BottomMenuItem
import com.example.mentalhealth.presentation.navigation.Screen
import com.example.mentalhealth.presentation.profile.SettingsItem

object Constants {
    val bottomMenuElementList = listOf(
        BottomMenuItem(
            "Journal",
            R.drawable.ic_home,
            Screen.JournalScreen.route
        ),
        BottomMenuItem(
            "Statistics",
            R.drawable.ic_stats,
            Screen.StatisticsScreen.route
        ),
        BottomMenuItem(
            "Recommendations",
            R.drawable.ic_recommended,
            Screen.RecommendationsScreen.route
        ),
        BottomMenuItem(
            "Account",
            R.drawable.ic_account,
            Screen.ProfileScreen.route
        )
    )

    val studiesList = listOf(
        "Accounting", "Agriculture", "Anthropology", "Architecture", "Art", "Biology",
        "Business Administration", "Chemistry", "Computer Science", "Criminal Justice",
        "Dentistry", "Economics", "Education", "Engineering", "English Literature",
        "Environmental Science", "Finance", "History", "Information Technology", "Journalism",
        "Law", "Linguistics", "Marketing", "Mathematics", "Mechanical Engineering", "Medicine",
        "Music", "Nursing", "Philosophy", "Physics", "Political Science", "Psychology",
        "Sociology", "Statistics", "Theater Arts", "Veterinary Science"
    )

    val occupationsList = listOf(
        "Accountant", "Actor/Actress", "Architect", "Artist", "Athlete",
        "Chef", "Civil Engineer", "Dentist", "Doctor", "Electrician",
        "Fashion Designer", "Firefighter", "Graphic Designer", "Journalist",
        "Lawyer", "Mechanic", "Musician", "Nurse", "Photographer", "Police Officer",
        "Professor", "Software Developer", "Teacher", "Veterinarian", "Waiter/Waitress"
    )

    val genderList = listOf("Male", "Female", "Prefer not to say")

    val maritalStatusList = listOf("Married", "Single", "Divorced")

    val livingAreaList = listOf("Rural", "Urban")

    val publicFigureList = listOf("Yes", "No")

    val profileScreenSettingsList = listOf(
        SettingsItem(R.string.account, Screen.AccountSettingsScreen.route),
        SettingsItem(R.string.notifications, Screen.NotificationsSettingsScreen.route)
    )

    val progressValues = listOf(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

    val todayFeelings = listOf(
        "Ecstatic", "Excited", "Happy", "Joyful", "Content", "Grateful", "Hopeful",
        "Proud", "Relaxed", "Calm", "Fine", "Satisfied", "Valued", "Productive",
        "Motivated", "Loved", "Tired", "Lazy", "Bored", "Annoyed", "Frustrated", "Anxious",
        "Insecure", "Sad", "Sick", "Lonely", "Depressed", "Angry", "Stressed"
    )

    val careOfMyself = listOf(
        "Walked", "Cooked", "Did Yoga", "Read", "Meditated", "Exercised", "Journaled",
        "Listened to music", "Watched a movie", "Spent time with family", "Spent time with friends",
        "Took a bath", "Practiced a hobby", "Gardened", "Danced", "Drew", "Painted", "Knitted", "Baked",
        "Played an instrument", "Cleaned", "Organized", "Studied", "Learned a new skill",
        "Took a nap", "Did a skincare routine", "Went for a run", "Rode a bike", "Played sports",
        "Did a puzzle", "Played a game", "Went to the spa", "Did crafts", "Practiced mindfulness",
        "Practiced gratitude", "Did stretching exercises", "Tried a new recipe",
        "Did breathing exercises", "Had a digital detox", "Volunteered", "Spent time in nature",
        "Practiced positive affirmations", "Planned a trip", "Watched a documentary","Visited a park",
        "Visited a museum", "Had a cup of tea/coffee",
        "Attended a cultural event", "Explored a new place", "Went to the beach",
        "Had a healthy meal", "Hydrated well", "Spent time with pets", "Reflected on goals",
        "Did light physical activity", "Watched a comedy show", "Explored spirituality"
    )

    val dayFeeling = listOf("Very bad", "Bad", "decent", "Good", "Very good")
}
