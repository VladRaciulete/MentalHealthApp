package com.example.mentalhealth.presentation.navigation

sealed class Screen(val route: String) {
    data object JournalScreen : Screen("journal_screen")
    data object MorningCheckInScreen : Screen("morning_check_in_screen")
    data object EveningCheckInScreen : Screen("evening_check_in_screen")
    data object StatisticsScreen : Screen("statistics_screen")
    data object RecommendationsScreen : Screen("recommendations_screen")
    data object ProfileScreen : Screen("profile_screen")
    data object AccountSettingsScreen : Screen("account_settings_screen")
    data object NotificationsSettingsScreen : Screen("notifications_settings_screen")
    data object LogInScreen : Screen("login_screen")
    data object SignUpScreenStep1 : Screen("signup_screen_step1")
    data object SignUpScreenStep2 : Screen("signup_screen_step2")
    data object SignUpScreenStep3 : Screen("signup_screen_step3")
    data object ForgotPasswordScreen : Screen("forgot_password_screen")
}