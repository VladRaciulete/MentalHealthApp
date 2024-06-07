package com.example.mentalhealth.presentation.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.presentation.bottomMenu.BottomMenu
import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.profile.SettingsItem
import com.example.mentalhealth.presentation.CustomProgressIndicator
import com.example.mentalhealth.presentation.profile.viewmodels.ProfileViewModel
import com.example.mentalhealth.ui.theme.*
import com.example.mentalhealth.utils.Constants
import com.example.mentalhealth.utils.UiState

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.loadUserData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AccentColor)
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    text = viewModel.currentUserData.value.firstName + " " + viewModel.currentUserData.value.lastName,
                    color = TextWhiteColor,
                    fontSize = 20.sp
                )
            }

            AccountScreenList(
                navController,
                Constants.profileScreenSettingsList
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.logOut()
                        navController.navigate("auth") {
                            popUpTo("journal") {
                                inclusive = true
                            }
                        }
                    }
            ) {
                Text(
                    text = stringResource(id = R.string.log_out),
                    color = Color.Red,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
        BottomMenu(
            navController = navController,
            items = Constants.bottomMenuElementList,
            initialSelectedItemIndex = 3,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
    when (viewModel.appStateViewModel.uiState.value) {
        is UiState.Idle -> {
        }

        is UiState.Loading -> {
            CustomProgressIndicator()
        }

        is UiState.Success -> {
        }

        is UiState.Error -> {
        }
    }
}

@Composable
fun AccountScreenList(
    navController: NavController,
    itemsList: List<SettingsItem>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(itemsList) { item ->
            AccountSettingsItem(navController, item)
        }
    }
}

@Composable
fun AccountSettingsItem(
    navController: NavController,
    item: SettingsItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(item.route)
            }
    ) {
        Text(
            text = stringResource(id = item.titleId),
            color = TextWhiteColor,
            modifier = Modifier.padding(20.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
    }
}