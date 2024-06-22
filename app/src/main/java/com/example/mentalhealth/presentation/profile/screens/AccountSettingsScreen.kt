package com.example.mentalhealth.presentation.profile.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.CustomAutocompleteField
import com.example.mentalhealth.presentation.CustomDatePicker
import com.example.mentalhealth.presentation.CustomDropDownMenu
import com.example.mentalhealth.presentation.JournalDatePicker
import com.example.mentalhealth.presentation.profile.viewmodels.ProfileViewModel
import com.example.mentalhealth.ui.theme.AccentColor
import com.example.mentalhealth.ui.theme.BackgroundColor
import com.example.mentalhealth.ui.theme.ErrorTextColor
import com.example.mentalhealth.ui.theme.TextFieldBackgroundColor
import com.example.mentalhealth.ui.theme.TextWhiteColor
import com.example.mentalhealth.ui.theme.UnfocusedTextWhiteColor
import com.example.mentalhealth.utils.Constants
import com.example.mentalhealth.utils.Validator

@Composable
fun AccountSettingsScreen(
    navController: NavController,
    viewModel: ProfileViewModel
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
                    contentDescription = "Back",
                    tint = TextWhiteColor,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )

                Text(
                    text = stringResource(id = R.string.account),
                    color = TextWhiteColor,
                    fontSize = 20.sp
                )

                Box(
                    modifier = Modifier
                        .size(width = 32.dp, height = 32.dp)
                        .padding(end = 10.dp)
                ){}
            }

            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                item {

                    Spacer(modifier = Modifier.height(40.dp))

                    OutlinedTextField(
                        value = viewModel.firstName.value,
                        onValueChange = { newValue ->
                            viewModel.firstName.value = newValue
                            viewModel.firstNameShowError.value = true
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.first_name),
                                color = UnfocusedTextWhiteColor
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = TextFieldBackgroundColor,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = TextWhiteColor,
                            unfocusedTextColor = UnfocusedTextWhiteColor,
                            errorTextColor = ErrorTextColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        maxLines = 1,
                        isError = viewModel.firstNameShowError.value && !Validator.validateFirstName(
                            viewModel.firstName.value
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        value = viewModel.lastName.value,
                        onValueChange = { newValue ->
                            viewModel.lastName.value = newValue
                            viewModel.lastNameShowError.value = true
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.last_name),
                                color = UnfocusedTextWhiteColor
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = TextFieldBackgroundColor,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = TextWhiteColor,
                            unfocusedTextColor = UnfocusedTextWhiteColor,
                            errorTextColor = ErrorTextColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        maxLines = 1,
                        isError = viewModel.lastNameShowError.value && !Validator.validateLastName(
                            viewModel.lastName.value
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    CustomDatePicker(
                        stringResource(id = R.string.pick_your_birthdate),
                        viewModel.birthDate
                    ) { newDate ->
                        viewModel.birthDate.value = newDate
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    CustomDropDownMenu(
                        itemList = Constants.genderList,
                        placeholder = stringResource(id = R.string.gender),
                        selectedItem = viewModel.gender,
                        onItemSelected = { selectedItem ->
                            viewModel.gender.value = selectedItem
                            viewModel.genderShowError.value = true
                        },
                        isError = viewModel.genderShowError.value && !Validator.validateGender(
                            viewModel.gender.value
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    CustomAutocompleteField(
                        stringResource(id = R.string.type_your_studies),
                        Constants.studiesList,
                        onItemSelected = { selectedItem ->
                            viewModel.studies.value = selectedItem
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    CustomAutocompleteField(
                        stringResource(id = R.string.type_your_occupation),
                        Constants.occupationsList,
                        onItemSelected = { selectedItem ->
                            viewModel.occupation.value = selectedItem
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    CustomDropDownMenu(
                        itemList = Constants.maritalStatusList,
                        placeholder = stringResource(id = R.string.marital_status),
                        selectedItem = viewModel.maritalStatus,
                        onItemSelected = { selectedItem ->
                            viewModel.maritalStatus.value = selectedItem
                            viewModel.maritalStatusShowError.value = true
                        },
                        isError = viewModel.maritalStatusShowError.value && !Validator.validateMaritalStatus(
                            viewModel.maritalStatus.value
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    CustomDropDownMenu(
                        itemList = Constants.livingAreaList,
                        placeholder = stringResource(id = R.string.living_area),
                        selectedItem = viewModel.livingArea,
                        onItemSelected = { selectedItem ->
                            viewModel.livingArea.value = selectedItem
                            viewModel.livingAreaShowError.value = true
                        },
                        isError = viewModel.livingAreaShowError.value && !Validator.validateLivingArea(
                            viewModel.livingArea.value
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    CustomDropDownMenu(
                        itemList = Constants.publicFigureList,
                        placeholder = stringResource(id = R.string.public_figure),
                        selectedItem = viewModel.publicFigure,
                        onItemSelected = { selectedItem ->
                            viewModel.publicFigure.value = selectedItem
                            viewModel.publicFigureShowError.value = true
                        },
                        isError = viewModel.publicFigureShowError.value && !Validator.validatePublicFigure(
                            viewModel.publicFigure.value
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    
                    Button(
                        onClick = {
                            viewModel.updateUserData()
                            navController.popBackStack()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.done))
                    }
                }
            }
        }
    }
}