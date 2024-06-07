package com.example.mentalhealth.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.mentalhealth.presentation.*
import com.example.mentalhealth.presentation.auth.screens.ForgotPasswordScreen
import com.example.mentalhealth.presentation.profile.viewmodels.ProfileViewModel
import com.example.mentalhealth.presentation.auth.screens.LogInScreen
import com.example.mentalhealth.presentation.auth.screens.SignUpScreenStep1
import com.example.mentalhealth.presentation.auth.screens.SignUpScreenStep2
import com.example.mentalhealth.presentation.auth.screens.SignUpScreenStep3
import com.example.mentalhealth.presentation.auth.viewmodels.LogInViewModel
import com.example.mentalhealth.presentation.auth.viewmodels.SignUpViewModel
import com.example.mentalhealth.presentation.profile.screens.AccountSettingsScreen
import com.example.mentalhealth.presentation.profile.screens.NotificationsSettingsScreen
import com.example.mentalhealth.presentation.profile.screens.ProfileScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    val logInViewModel: LogInViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = if (logInViewModel.checkUserAuthenticatedUseCase()) "journal" else "auth",
        enterTransition = { fadeIn(animationSpec = tween(10)) },
        exitTransition = { fadeOut(animationSpec = tween(10)) }
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

            composable(route = Screen.ProfileScreen.route) {
                ProfileScreen(navController = navController, viewModel = profileViewModel)
            }

            composable(route = Screen.AccountSettingsScreen.route) {
                AccountSettingsScreen(navController = navController, viewModel = profileViewModel)
            }

            composable(route = Screen.NotificationsSettingsScreen.route) {
                NotificationsSettingsScreen(navController = navController, viewModel = profileViewModel)
            }
        }
    }
}