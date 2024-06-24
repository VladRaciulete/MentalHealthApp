package com.example.mentalhealth.presentation.statistics.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.CustomPercentageComponent
import com.example.mentalhealth.presentation.CustomSpacerBorder
import com.example.mentalhealth.presentation.CustomTimeRangePicker
import com.example.mentalhealth.presentation.bottomMenu.BottomMenu
import com.example.mentalhealth.presentation.statistics.viewmodels.StatisticsViewModel
import com.example.mentalhealth.ui.theme.AccentColor
import com.example.mentalhealth.ui.theme.BackgroundColor
import com.example.mentalhealth.ui.theme.FocusedBorderColor
import com.example.mentalhealth.ui.theme.JournalEntryBackgroundColor2
import com.example.mentalhealth.ui.theme.TextWhiteColor
import com.example.mentalhealth.utils.Constants

@Composable
fun StatisticsScreen(
    navController: NavController,
    viewModel: StatisticsViewModel
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
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AccentColor)
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.statistics),
                    color = TextWhiteColor,
                    fontSize = 20.sp
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    CustomTimeRangePicker(
                        viewModel.statisticsTimeRange,
                        Constants.statisticsTimeRangeList,
                        onItemSelected = {
                            viewModel.loadStatistics()
                        }
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Text(
                        text = stringResource(id = R.string.average_wake_up_time) +
                                ": ${viewModel.averageWakeUpTime.value}",
                        color = TextWhiteColor,
                        fontSize = 20.sp
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Text(
                        text = stringResource(id = R.string.average_hours_slept) +
                                ": ${viewModel.averageHoursSlept.value}",
                        color = TextWhiteColor,
                        fontSize = 20.sp
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Text(
                        text = stringResource(id = R.string.average_day_rating) +
                                ": ${viewModel.averageDayRating.value}/5",
                        color = TextWhiteColor,
                        fontSize = 20.sp
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Text(
                        text = stringResource(id = R.string.average_goal_progress) +
                                ": ${viewModel.averageGoalProgress.value}%",
                        color = TextWhiteColor,
                        fontSize = 20.sp
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    CustomPercentageComponent(
                        title = stringResource(id = R.string.your_daily_moods),
                        items = viewModel.dayMoods
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Text(
                        text = stringResource(id = R.string.average_stress_level) +
                                ": ${viewModel.averageStressLevel.value}/5",
                        color = TextWhiteColor,
                        fontSize = 20.sp
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(JournalEntryBackgroundColor2)
                    ) {
                        Text(
                            text = stringResource(id = R.string.average_water_intake) +
                                    ": ${viewModel.averageWaterIntake.value}/5",
                            color = TextWhiteColor,
                            fontSize = 20.sp
                        )
                    }

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Text(
                        text = stringResource(id = R.string.average_energy_level) +
                                ": ${viewModel.averageEnergyLevel.value}/5",
                        color = TextWhiteColor,
                        fontSize = 20.sp
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)


                    Text(
                        text = stringResource(id = R.string.average_love_level) +
                                ": ${viewModel.averageLoveLevel.value}/5",
                        color = TextWhiteColor,
                        fontSize = 20.sp
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    CustomPercentageComponent(
                        title = stringResource(id = R.string.your_daily_feelings),
                        items = viewModel.dayFeelings
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    CustomPercentageComponent(
                        title = stringResource(id = R.string.how_you_took_care_of_yourself),
                        items = viewModel.careOfYourself
                    )

                    Spacer(modifier = Modifier.height(120.dp))
                }
            }
        }

        BottomMenu(
            navController = navController,
            items = Constants.bottomMenuElementList,
            initialSelectedItemIndex = 2,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}