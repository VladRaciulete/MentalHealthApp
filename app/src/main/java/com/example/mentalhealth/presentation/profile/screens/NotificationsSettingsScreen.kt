package com.example.mentalhealth.presentation.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.profile.viewmodels.ProfileViewModel
import com.example.mentalhealth.ui.theme.AccentColor
import com.example.mentalhealth.ui.theme.BackgroundColor
import com.example.mentalhealth.ui.theme.TextWhiteColor

@Composable
fun NotificationsSettingsScreen(
    navController: NavController,
    viewModel: ProfileViewModel
) {
    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AccentColor)
                .padding(vertical = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .clickable {
                        navController.popBackStack()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Back",
                    tint = TextWhiteColor
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = stringResource(id = R.string.notifications),
                    color = TextWhiteColor,
                    fontSize = 20.sp
                )
            }
        }
    }
}