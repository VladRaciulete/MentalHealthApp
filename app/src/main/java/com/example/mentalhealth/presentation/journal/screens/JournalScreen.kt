package com.example.mentalhealth.presentation.journal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.mentalhealth.presentation.JournalDatePicker
import com.example.mentalhealth.presentation.bottomMenu.BottomMenu
import com.example.mentalhealth.presentation.journal.viewmodels.JournalViewModel
import com.example.mentalhealth.presentation.navigation.Screen
import com.example.mentalhealth.ui.theme.*
import com.example.mentalhealth.utils.Constants

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
                    .padding(vertical = 16.dp)
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
                    viewModel.getJournalEntry()
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

                if (!viewModel.morningCheckIn.value) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(Screen.MorningCheckInScreen.route)
                            }
                        ) {
                            Text(text = stringResource(id = R.string.morning_journal))
                        }
                    }
                } else if (viewModel.morningCheckIn.value && !viewModel.eveningCheckIn.value) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(Screen.EveningCheckInScreen.route)
                            }
                        ) {
                            Text(text = stringResource(id = R.string.evening_journal))
                        }
                    }

                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.wake_up_time) + ": " + viewModel.wakeUpTime.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.hours_slept) + ": " + viewModel.hoursSlept.value,
                                    color = TextWhiteColor
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.daily_goals),
                                    color = TextWhiteColor
                                )

                                viewModel.dailyGoals.value.forEach { goal->
                                    Text(
                                        text = goal.description,
                                        color = TextWhiteColor,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.wake_up_time) + ": " + viewModel.wakeUpTime.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.hours_slept) + ": " + viewModel.hoursSlept.value,
                                    color = TextWhiteColor
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.daily_goals),
                                    color = TextWhiteColor
                                )

                                viewModel.dailyGoals.value.forEach { goal->
                                    Text(
                                        text = "${goal.description} - ${goal.progress}%",
                                        color = TextWhiteColor,
                                        fontSize = 14.sp
                                    )
                                }
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.you_are_grateful_for) + ": ",
                                    color = TextWhiteColor
                                )
                                Text(
                                    text = viewModel.todayIAmGratefulFor.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.today_you_felt) + ": " + viewModel.todayIFelt.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.stress_level) + ": " + viewModel.stressLevel.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.water_intake) + ": " + viewModel.waterIntake.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.energy_level) + ": " + viewModel.energyLevel.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.love_level) + ": " + viewModel.loveLevel.value,
                                    color = TextWhiteColor
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.did_you_have_enough),
                                    color = TextWhiteColor
                                )

                                Spacer(modifier = Modifier.height(15.dp))

                                for (asd in viewModel.didIHaveEnough.value) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth(0.4f)
                                    ) {
                                        Text(
                                            text = asd.key,
                                            color = TextWhiteColor
                                        )
                                        Text(
                                            text = if (asd.value) stringResource(id = R.string.yes) else stringResource(id = R.string.no),
                                            color = TextWhiteColor
                                        )
                                    }
                                }
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.what_went_well) + ": ",
                                    color = TextWhiteColor
                                )
                                Text(
                                    text = viewModel.whatWentWell.value,
                                    color = TextWhiteColor
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.what_went_bad) + ": ",
                                    color = TextWhiteColor
                                )
                                Text(
                                    text = viewModel.whatWentBad.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.you_took_care_of_yourself) + ": " + viewModel.whatDidIDoToTakeCareOfMyself.value,
                                    color = TextWhiteColor
                                )
                            }


                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.best_moment_of_the_day) + ": ",
                                    color = TextWhiteColor
                                )
                                Text(
                                    text = viewModel.bestMomentOfTheDay.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.day_rating) + ": " + viewModel.dayRating.value,
                                    color = TextWhiteColor
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor2)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.your_day_was) + ": " + viewModel.dayFeeling.value,
                                    color = TextWhiteColor
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(JournalEntryBackgroundColor1)
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.how_can_you_make_tomorrow_better) + ": ",
                                    color = TextWhiteColor
                                )
                                Text(
                                    text = viewModel.whatCanIDoToMakeTomorrowBetter.value,
                                    color = TextWhiteColor
                                )
                            }

                            Spacer(modifier = Modifier.height(120.dp))
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
