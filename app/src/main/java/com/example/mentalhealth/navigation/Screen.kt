package com.example.mentalhealth.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object StatisticsScreen : Screen("statistics_screen")
    object RecommendationsScreen : Screen("recommendations_screen")
    object AccountScreen : Screen("account_screen")
    object LogInScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object ForgotPasswordScreen : Screen("forgot_password_screen")
}