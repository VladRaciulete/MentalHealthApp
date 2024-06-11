package com.example.mentalhealth.presentation.journal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.JournalDatePicker
import com.example.mentalhealth.presentation.bottomMenu.BottomMenu
import com.example.mentalhealth.presentation.journal.viewmodels.JournalViewModel
import com.example.mentalhealth.presentation.navigation.Screen
import com.example.mentalhealth.ui.theme.*
import com.example.mentalhealth.utils.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun JournalScreen(
    navController: NavController,
    viewModel: JournalViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AccentColor)
                    .padding(vertical = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Previous",
                    tint = TextWhiteColor,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            viewModel.decrementDate()
                            viewModel.getJournalEntry()
                        }
                )

                JournalDatePicker(
                    viewModel.date.value
                ) { newDate ->
                    viewModel.date.value = newDate
                }

                Icon(
                    painter = painterResource(id = R.drawable.ic_forward_arrow),
                    contentDescription = "Next",
                    tint = TextWhiteColor,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable {
                            viewModel.incrementDate()
                            viewModel.getJournalEntry()
                        }
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(!viewModel.morningCheckIn.value){
                    Button(
                        onClick = {
                            navController.navigate(Screen.MorningCheckInScreen.route)
                        }
                    ) {
                        Text(text = "Morning Check In")
                    }
                } else {
                    if(!viewModel.eveningCheckIn.value){
                        Button(
                            onClick = {
                                navController.navigate(Screen.EveningCheckInScreen.route)
                            }
                        ) {
                            Text(text = "Evening Check In")
                        }
                    } else {

                        LazyColumn(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            item {
                                Text(text = "wakeUpTime: " + viewModel.wakeUpTime.value, color = TextWhiteColor)
                                Text(text = "hoursSlept: " + viewModel.hoursSlept.value, color = TextWhiteColor)
                                Text(text = "dailyGoals: " + viewModel.dailyGoals.value, color = TextWhiteColor)
                                Text(text = "todayIAmGratefulFor: " + viewModel.todayIAmGratefulFor.value, color = TextWhiteColor)
                                Text(text = "todayIFelt: " + viewModel.todayIFelt.value, color = TextWhiteColor)
                                Text(text = "stressLevel: " + viewModel.stressLevel.value, color = TextWhiteColor)
                                Text(text = "waterIntake: " + viewModel.waterIntake.value, color = TextWhiteColor)
                                Text(text = "energyLevel: " + viewModel.energyLevel.value, color = TextWhiteColor)
                                Text(text = "loveLevel: " + viewModel.loveLevel.value, color = TextWhiteColor)
                                Text(text = "didIHaveEnough: " + viewModel.didIHaveEnough.value, color = TextWhiteColor)
                                Text(text = "whatWentWell: " + viewModel.whatWentWell.value, color = TextWhiteColor)
                                Text(text = "whatWentBad: " + viewModel.whatWentBad.value, color = TextWhiteColor)
                                Text(text = "whatDidIDoToTakeCareOfMyself: " + viewModel.whatDidIDoToTakeCareOfMyself.value, color = TextWhiteColor)
                                Text(text = "bestMomentOfTheDay: " + viewModel.bestMomentOfTheDay.value, color = TextWhiteColor)
                                Text(text = "dayRating: " + viewModel.dayRating.value, color = TextWhiteColor)
                                Text(text = "dayFeeling: " + viewModel.dayFeeling.value, color = TextWhiteColor)
                                Text(text = "whatCanIDoToMakeTomorrowBetter: " + viewModel.whatCanIDoToMakeTomorrowBetter.value, color = TextWhiteColor)
                                Text(text = "timeStamp: " + viewModel.timeStamp.value, color = TextWhiteColor)
                                Text(text = "date: " + viewModel.date.value, color = TextWhiteColor)
                            }
                        }
                    }
                }
            }
        }

        BottomMenu(
            navController = navController,
            items = Constants.bottomMenuElementList,
            initialSelectedItemIndex = 0,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
