package com.example.mentalhealth.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.mentalhealth.presentation.*
import com.example.mentalhealth.presentation.auth.*

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val signUpViewModel: SignUpViewModel = viewModel()
    val logInViewModel: LogInViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = if(logInViewModel.checkUserAuthenticatedUseCase()) "journal" else "auth"
    ) {
        navigation(
            startDestination = Screen.LogInScreen.route,
            route = "auth",
        ) {
            composable(route = Screen.LogInScreen.route) {
                LogInScreen(navController = navController, viewModel = logInViewModel)
            }

            composable(route = Screen.SignUpScreenStep1.route) {
                SignUpScreenStep1(navController = navController, viewModel = signUpViewModel)
            }

            composable(route = Screen.SignUpScreenStep2.route) {
                SignUpScreenStep2(navController = navController, viewModel = signUpViewModel)
            }

            composable(route = Screen.SignUpScreenStep3.route) {
                SignUpScreenStep3(navController = navController, viewModel = signUpViewModel)
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