package com.example.mentalhealth.presentation.recommendations.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.CustomSpacerBorder
import com.example.mentalhealth.presentation.JournalDatePicker
import com.example.mentalhealth.presentation.bottomMenu.BottomMenu
import com.example.mentalhealth.presentation.navigation.Screen
import com.example.mentalhealth.presentation.recommendations.viewmodels.RecommendationsViewModel
import com.example.mentalhealth.ui.theme.AccentColor
import com.example.mentalhealth.ui.theme.BackgroundColor
import com.example.mentalhealth.ui.theme.FocusedBorderColor
import com.example.mentalhealth.ui.theme.TextWhiteColor
import com.example.mentalhealth.utils.Constants


@Composable
fun DailyRecommendationsScreen(
    navController: NavController,
    viewModel: RecommendationsViewModel
){
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
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .size(width = 32.dp, height = 32.dp)
                        .padding(start = 30.dp)
                ){}

                Text(
                    text = stringResource(id = R.string.recommendations),
                    color = TextWhiteColor,
                    fontSize = 20.sp
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = "Back",
                    tint = TextWhiteColor,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            navController.navigate(Screen.RecommendationsHistoryScreen.route)
                        }
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(viewModel.loadedData.value){
                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Text(
                        text = stringResource(id = R.string.predicted_mood) + ":",
                        color = TextWhiteColor
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = viewModel.moodPrediction.value,
                        color = TextWhiteColor
                    )

                    CustomSpacerBorder(20, 20, FocusedBorderColor)

                    Text(
                        text = stringResource(id = R.string.recommendations) + ":",
                        color = TextWhiteColor
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = viewModel.recommendation1.value,
                        color = TextWhiteColor
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = viewModel.recommendation2.value,
                        color = TextWhiteColor
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = viewModel.recommendation3.value,
                        color = TextWhiteColor
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = viewModel.recommendation4.value,
                        color = TextWhiteColor
                    )
                    CustomSpacerBorder(20, 20, FocusedBorderColor)
                } else {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(id = R.string.no_recommendations_found),
                        color = TextWhiteColor,
                        fontSize = 20.sp
                    )
                }
            }
        }

        BottomMenu(
            navController = navController,
            items = Constants.bottomMenuElementList,
            initialSelectedItemIndex = 1,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
