package com.example.mentalhealth.utils

import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.bottomMenu.BottomMenuItem
import com.example.mentalhealth.presentation.navigation.Screen
import com.example.mentalhealth.presentation.profile.SettingsItem

object Constants {
    val bottomMenuElementList = listOf(
        BottomMenuItem("Journal", R.drawable.ic_home, Screen.JournalScreen.route),
        BottomMenuItem("Statistics", R.drawable.ic_stats, Screen.StatisticsScreen.route),
        BottomMenuItem("Recommendations", R.drawable.ic_recommended, Screen.RecommendationsScreen.route),
        BottomMenuItem("Account", R.drawable.ic_account, Screen.ProfileScreen.route)
    )

    val profileScreenSettingsList = listOf(
        SettingsItem(R.string.account, Screen.AccountSettingsScreen.route),
        SettingsItem(R.string.notifications, Screen.NotificationsSettingsScreen.route)
    )

    val progressValues = listOf(0,10,20,30,40,50,60,70,80,90,100)

    val todayPositiveFeelings = listOf(
        "calm", "fine", "content", "excited", "grateful", "happy",
        "hopeful", "joyful", "loved", "motivated", "powerful",
        "productive", "proud", "relaxed", "satisfied", "valued",
    )

    val todayNegativeFeelings = listOf(
        "angry","annoyed","frustrated","bored",
        "depressed","anxious","insecure",
        "lazy","lonely","sad","tired","sick"
    )

    val careOfMyself = listOf(
        "walk", "bath", "pets", "cook", "yoga", "read",
        "journaling", "extra sleep", "met family",
        "met friends", "played games", "exercise", "workout",
        "tv/movie", "meditate", "running", "food", "music",
    )

    val dayFeeling = listOf("very bad", "bad", "decent", "good", "very good")
}
