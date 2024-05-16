package com.example.mentalhealth.navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object StatisticsScreen : Screen("statistics_screen")
    data object RecommendationsScreen : Screen("recommendations_screen")
    data object AccountScreen : Screen("account_screen")
    data object LogInScreen : Screen("login_screen")
    data object SignUpScreen : Screen("signup_screen")
    data object SignUpScreenStep1 : Screen("signup_screen_step1")
    data object SignUpScreenStep2 : Screen("signup_screen_step2")
    data object SignUpScreenStep3 : Screen("signup_screen_step3")
    data object ForgotPasswordScreen : Screen("forgot_password_screen")
}