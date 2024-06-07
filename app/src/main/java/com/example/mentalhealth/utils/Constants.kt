package com.example.mentalhealth.utils

import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.bottomMenu.BottomMenuItem
import com.example.mentalhealth.presentation.navigation.Screen
import com.example.mentalhealth.presentation.profile.SettingsItem

object Constants {
    val bottomMenuElementList = listOf(
        BottomMenuItem("Home", R.drawable.ic_home, Screen.HomeScreen.route),
        BottomMenuItem("Statistics", R.drawable.ic_stats, Screen.StatisticsScreen.route),
        BottomMenuItem("Recommendations", R.drawable.ic_recommended, Screen.RecommendationsScreen.route),
        BottomMenuItem("Account", R.drawable.ic_account, Screen.ProfileScreen.route)
    )

    val profileScreenSettingsList = listOf(
        SettingsItem(R.string.account, Screen.AccountSettingsScreen.route),
        SettingsItem(R.string.notifications, Screen.NotificationsSettingsScreen.route)
    )
}
