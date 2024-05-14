package com.example.mentalhealth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.mentalhealth.screens.*

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = Screen.LogInScreen.route,
            route = "auth",
        ) {
            composable(route = Screen.LogInScreen.route) {
                LogInScreen(navController = navController)
            }

            composable(route = Screen.SignUpScreen.route) {
                SignUpScreen(navController = navController)
            }

            composable(route = Screen.ForgotPasswordScreen.route) {
                ForgotPasswordScreen(navController = navController)
            }
        }

        navigation(
            startDestination = Screen.HomeScreen.route,
            route = "journal",
        ) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }

            composable(route = Screen.StatisticsScreen.route) {
                StatisticsScreen(navController = navController)
            }

            composable(route = Screen.RecommendationsScreen.route) {
                RecommendationsScreen(navController = navController)
            }

            composable(route = Screen.AccountScreen.route) {
                AccountScreen(navController = navController)
            }
        }
    }
}