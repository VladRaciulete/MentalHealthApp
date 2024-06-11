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
import com.example.mentalhealth.presentation.AddGoalsProgress
import com.example.mentalhealth.presentation.CustomLevelPicker
import com.example.mentalhealth.presentation.CustomSpacerBorder
import com.example.mentalhealth.presentation.CustomToggle
import com.example.mentalhealth.presentation.FeelingPicker
import com.example.mentalhealth.presentation.FeelingsPicker
import com.example.mentalhealth.presentation.RatingStars
import com.example.mentalhealth.presentation.journal.viewmodels.JournalViewModel
import com.example.mentalhealth.ui.theme.*
import com.example.mentalhealth.utils.Constants

@Composable
fun EveningCheckInScreen(
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
                    text = "Evening check in",
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
                Spacer(modifier = Modifier.height(40.dp))

                RatingStars("Day Rating", viewModel.dayRating)

                CustomSpacerBorder(15,15, FocusedBorderColor)

                AddGoalsProgress("Add goals progress",viewModel.dailyGoals)

                CustomSpacerBorder(15,15, FocusedBorderColor)

                FeelingsPicker(
                    text = "todayIFelt",
                    Constants.todayPositiveFeelings,
                    Constants.todayNegativeFeelings,
                    state = viewModel.todayIFelt
                )

                CustomSpacerBorder(15,15, FocusedBorderColor)

                CustomLevelPicker("Stress Level", viewModel.stressLevel)

                CustomSpacerBorder(15,15, FocusedBorderColor)

                CustomLevelPicker("Water Intake", viewModel.waterIntake)

                CustomSpacerBorder(15,15, FocusedBorderColor)

                CustomLevelPicker("Energy Level", viewModel.energyLevel)

                CustomSpacerBorder(15,15, FocusedBorderColor)

                CustomLevelPicker("Love Level", viewModel.loveLevel)

                CustomSpacerBorder(15,15, FocusedBorderColor)

                FeelingPicker(
                    text = "dayFeeling",
                    Constants.dayFeeling,
                    state = viewModel.dayFeeling
                )

                CustomSpacerBorder(15,15, FocusedBorderColor)

                CustomToggle(text = "DID I HAVE ENOUGH",state = viewModel.didIHaveEnough)

                CustomSpacerBorder(15,15, FocusedBorderColor)

                FeelingPicker(
                    text = "whatDidIDoToTakeCareOfMyself",
                    Constants.careOfMyself,
                    state = viewModel.whatDidIDoToTakeCareOfMyself
                )

                CustomSpacerBorder(15,15, FocusedBorderColor)

                OutlinedTextField(
                    value = viewModel.todayIAmGratefulFor.value,
                    onValueChange = { newValue ->
                        viewModel.todayIAmGratefulFor.value = newValue
                    },
                    label = { Text(text = "todayIAmGratefulFor") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = TextFieldBackgroundColor,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = TextWhiteColor,
                        unfocusedTextColor = UnfocusedTextWhiteColor,
                        errorTextColor = ErrorTextColor
                    ),
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.whatWentWell.value,
                    onValueChange = { newValue ->
                        viewModel.whatWentWell.value = newValue
                    },
                    label = {
                        Text(text = "whatWentWell")
                    },
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

                OutlinedTextField(
                    value = viewModel.whatWentBad.value,
                    onValueChange = { newValue ->
                        viewModel.whatWentBad.value = newValue
                    },
                    label = {
                        Text(text = "whatWentBad")
                    },
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

                OutlinedTextField(
                    value = viewModel.bestMomentOfTheDay.value,
                    onValueChange = { newValue ->
                        viewModel.bestMomentOfTheDay.value = newValue
                    },
                    label = {
                        Text(text = "bestMomentOfTheDay")
                    },
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

                OutlinedTextField(
                    value = viewModel.whatCanIDoToMakeTomorrowBetter.value,
                    onValueChange = { newValue ->
                        viewModel.whatCanIDoToMakeTomorrowBetter.value = newValue
                    },
                    label = {
                        Text(text = "whatCanIDoToMakeTomorrowBetter")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = TextFieldBackgroundColor,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = TextWhiteColor,
                        unfocusedTextColor = UnfocusedTextWhiteColor,
                        errorTextColor = ErrorTextColor
                    ),
                    singleLine = true
                )

                CustomSpacerBorder(20,15, FocusedBorderColor)

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        viewModel.addEveningJournalCheckIn()
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "Check In")
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}
