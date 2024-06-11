package com.example.mentalhealth.presentation.journal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.AddGoals
import com.example.mentalhealth.presentation.CustomSpacerBorder
import com.example.mentalhealth.presentation.journal.viewmodels.JournalViewModel
import com.example.mentalhealth.ui.theme.*
import com.example.mentalhealth.presentation.CustomTimePicker

@Composable
fun MorningCheckInScreen(
    navController: NavController,
    viewModel: JournalViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
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
                    text = "Morning check in",
                    color = TextWhiteColor,
                    fontSize = 20.sp
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Spacer(modifier = Modifier.height(15.dp))

                CustomTimePicker(viewModel.wakeUpTime)

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(
                    value = viewModel.hoursSlept.value,
                    onValueChange = { newValue ->
                        viewModel.hoursSlept.value = newValue
                    },
                    label = { Text(text = "hoursSlept") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = TextFieldBackgroundColor,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = TextWhiteColor,
                        unfocusedTextColor = UnfocusedTextWhiteColor,
                        errorTextColor = ErrorTextColor
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(15.dp))
                viewModel.dailyGoals.value.forEachIndexed { index, item ->
                    println("Goal($index) => ${item.description}")
                }

                CustomSpacerBorder(15, 15, FocusedBorderColor)

                AddGoals(
                    text = "Add your daily goals",
                    state = viewModel.dailyGoals
                )

                CustomSpacerBorder(15, 15, FocusedBorderColor)

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        viewModel.addMorningJournalCheckIn()
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "Check In")
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
