package com.example.mentalhealth.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mentalhealth.presentation.CustomDatePicker
import com.example.mentalhealth.presentation.CustomDropDownMenu
import com.example.mentalhealth.R
import com.example.mentalhealth.domain.usecase.validator.Validator
import com.example.mentalhealth.navigation.Screen
import com.example.mentalhealth.presentation.CustomProgressIndicator
import com.example.mentalhealth.ui.theme.*
import com.example.mentalhealth.utils.UiState

@Composable
fun SignUpScreenStep1(navController: NavController, viewModel: SignUpViewModel) {
    val appContext = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            Text(text = stringResource(id = R.string.create_an_account),
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = viewModel.firstName.value,
                onValueChange = { newValue ->
                    viewModel.firstName.value = newValue
                    viewModel.firstNameShowError.value = true
                },
                label = {
                    Text(text = stringResource(id = R.string.first_name), color = UnfocusedTextWhiteColor)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor  = TextFieldBackgroundColor,
                    unfocusedContainerColor  = Color.Transparent,
                    focusedTextColor = TextWhiteColor,
                    unfocusedTextColor = UnfocusedTextWhiteColor,
                    errorTextColor = ErrorTextColor
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                isError = viewModel.firstNameShowError.value && !Validator.validateFirstName(viewModel.firstName.value)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = viewModel.lastName.value,
                onValueChange = { newValue ->
                    viewModel.lastName.value = newValue
                    viewModel.lastNameShowError.value = true
                },
                label = {
                    Text(text = stringResource(id = R.string.last_name), color = UnfocusedTextWhiteColor)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor  = TextFieldBackgroundColor,
                    unfocusedContainerColor  = Color.Transparent,
                    focusedTextColor = TextWhiteColor,
                    unfocusedTextColor = UnfocusedTextWhiteColor,
                    errorTextColor = ErrorTextColor
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                isError = viewModel.lastNameShowError.value && !Validator.validateLastName(viewModel.lastName.value)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = viewModel.emailAddress.value,
                onValueChange = { newValue ->
                    viewModel.emailAddress.value = newValue
                    viewModel.emailAddressShowError.value = true
                },
                label = {
                    Text(text = stringResource(id = R.string.email_address), color = UnfocusedTextWhiteColor)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor  = TextFieldBackgroundColor,
                    unfocusedContainerColor  = Color.Transparent,
                    focusedTextColor = TextWhiteColor,
                    unfocusedTextColor = UnfocusedTextWhiteColor,
                    errorTextColor = ErrorTextColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                isError = viewModel.emailAddressShowError.value && !Validator.validateEmailAddress(viewModel.emailAddress.value)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = viewModel.password.value,
                onValueChange = { newValue ->
                    viewModel.password.value = newValue
                    viewModel.passwordShowError.value = true
                },
                label = {
                    Text(text = stringResource(id = R.string.password), color = UnfocusedTextWhiteColor)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor  = TextFieldBackgroundColor,
                    unfocusedContainerColor  = Color.Transparent,
                    focusedTextColor = TextWhiteColor,
                    unfocusedTextColor = UnfocusedTextWhiteColor,
                    errorTextColor = ErrorTextColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                ),
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.passwordVisible.value = !viewModel.passwordVisible.value
                        }
                    ) {
                        if(viewModel.passwordVisible.value) {
                            Icon(Icons.Filled.Visibility,contentDescription = "Hide Password")
                        }
                        else {
                            Icon(Icons.Filled.VisibilityOff,contentDescription = "Show Password")
                        }
                    }
                },
                visualTransformation = if(viewModel.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                isError = viewModel.passwordShowError.value && !Validator.validatePassword(viewModel.password.value)
            )
        }

        var signUpCurrentStep = viewModel.currentStep.value

        Text(
            text = stringResource(id = R.string.step) + " $signUpCurrentStep / 3",
            color = TextWhiteColor,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
        )

        Button(
            onClick = {
                viewModel.currentStep.value--
                if (navController.popBackStack()) {
                }
                else {
                    navController.navigate(Screen.LogInScreen.route)
                }

            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 25.dp, start = 15.dp)
        ) {
            Text(text = stringResource(id = R.string.back), color = TextWhiteColor)
        }

        var pleaseFillInTheFiledsText = stringResource(id = R.string.please_fill_in_the_fields)

        Button(
            onClick = {
                if (viewModel.validateFieldsScreen1()){
                    viewModel.currentStep.value++
                    navController.navigate(Screen.SignUpScreenStep2.route)
                }
                else {
                    Toast.makeText(appContext, pleaseFillInTheFiledsText, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 25.dp, end = 15.dp)
        ) {
            Text(text = stringResource(id = R.string.next), color = TextWhiteColor)
        }
    }
}

@Composable
fun SignUpScreenStep2(navController: NavController, viewModel: SignUpViewModel) {
    val appContext = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            Text(text = stringResource(id = R.string.create_an_account),
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(40.dp))

            //viewModel.birthDateShowError.value = true
            //viewModel.birthDateShowError.value && !Validator.validateDate(viewModel.birthDate.value)
            CustomDatePicker(viewModel.birthDate) {
                    newDate -> viewModel.birthDate.value = newDate
            }

            Spacer(modifier = Modifier.height(15.dp))

            CustomDropDownMenu(
                itemList = viewModel.genderList,
                placeholder = stringResource(id = R.string.gender),
                selectedItem = viewModel.gender,
                onItemSelected = { selectedItem ->
                    viewModel.gender.value = selectedItem
                    viewModel.genderShowError.value = true
                    println("gender " + viewModel.gender.value)
                },
                isError = viewModel.genderShowError.value && !Validator.validateGender(viewModel.gender.value)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = viewModel.profession.value,
                onValueChange = { newValue ->
                    viewModel.profession.value = newValue
                    viewModel.professionShowError.value = true
                },
                label = {
                    Text(text = stringResource(id = R.string.profession), color = UnfocusedTextWhiteColor)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor  = TextFieldBackgroundColor,
                    unfocusedContainerColor  = Color.Transparent,
                    focusedTextColor = TextWhiteColor,
                    unfocusedTextColor = UnfocusedTextWhiteColor,
                    errorTextColor = ErrorTextColor
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                isError = viewModel.professionShowError.value && !Validator.validateProfession(viewModel.profession.value)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = viewModel.occupation.value,
                onValueChange = { newValue ->
                    viewModel.occupation.value = newValue
                    viewModel.occupationShowError.value = true
                },
                label = {
                    Text(text = stringResource(id = R.string.occupation), color = UnfocusedTextWhiteColor)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor  = TextFieldBackgroundColor,
                    unfocusedContainerColor  = Color.Transparent,
                    focusedTextColor = TextWhiteColor,
                    unfocusedTextColor = UnfocusedTextWhiteColor,
                    errorTextColor = ErrorTextColor
                ),
                singleLine = true,
                maxLines = 1,
                isError = viewModel.occupationShowError.value && !Validator.validateOccupation(viewModel.occupation.value)
            )
        }

        var signUpCurrentStep = viewModel.currentStep.value

        Text(
            text = stringResource(id = R.string.step) + " $signUpCurrentStep / 3",
            color = TextWhiteColor,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
        )

        Button(
            onClick = {
                viewModel.currentStep.value--
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 25.dp, start = 15.dp)
        ) {
            Text(text = stringResource(id = R.string.back), color = TextWhiteColor)
        }

        var pleaseFillInTheFiledsText = stringResource(id = R.string.please_fill_in_the_fields)

        Button(
            onClick = {
                if (viewModel.validateFieldsScreen2()){
                    viewModel.currentStep.value++
                    navController.navigate(Screen.SignUpScreenStep3.route)
                }
                else {
                    Toast.makeText(appContext, pleaseFillInTheFiledsText, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 25.dp, end = 15.dp)
        ) {
            Text(text = stringResource(id = R.string.next), color = TextWhiteColor)
        }
    }
}

@Composable
fun SignUpScreenStep3(navController: NavController, viewModel: SignUpViewModel) {
    val appContext = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            Text(text = stringResource(id = R.string.create_an_account),
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomDropDownMenu(
                itemList = viewModel.maritalStatusList,
                placeholder = stringResource(id = R.string.marital_status),
                selectedItem = viewModel.maritalStatus,
                onItemSelected = { selectedItem ->
                    viewModel.maritalStatus.value = selectedItem
                    viewModel.maritalStatusShowError.value = true
                    println("maritalStatus " + viewModel.maritalStatus.value)
                },
                isError = viewModel.maritalStatusShowError.value && !Validator.validateMaritalStatus(viewModel.maritalStatus.value)
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomDropDownMenu(
                itemList = viewModel.livingAreaList,
                placeholder = stringResource(id = R.string.living_area),
                selectedItem = viewModel.livingArea,
                onItemSelected = { selectedItem ->
                    viewModel.livingArea.value = selectedItem
                    viewModel.livingAreaShowError.value = true
                    println("livingArea " + viewModel.livingArea.value)
                },
                isError = viewModel.livingAreaShowError.value && !Validator.validateLivingArea(viewModel.livingArea.value)
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomDropDownMenu(
                itemList = viewModel.publicFigureList,
                placeholder = stringResource(id = R.string.public_figure),
                selectedItem = viewModel.publicFigure,
                onItemSelected = { selectedItem ->
                    viewModel.publicFigure.value = selectedItem
                    viewModel.publicFigureShowError.value = true
                    println("publicFigure " + viewModel.publicFigure.value)
                },
                isError = viewModel.publicFigureShowError.value && !Validator.validatePublicFigure(viewModel.publicFigure.value)
            )

            Spacer(modifier = Modifier.height(15.dp))

            var pleaseFillInTheFiledsText = stringResource(id = R.string.please_fill_in_the_fields)

            var accountCreatedSuccessfully = stringResource(id = R.string.account_created_successfully)

            Button(
                onClick = {
                    if (viewModel.validateFieldsScreen3()){
                        viewModel.signUp()

                        Toast.makeText(appContext, accountCreatedSuccessfully, Toast.LENGTH_SHORT).show()

                        navController.popBackStack(Screen.LogInScreen.route,false)
                    }
                    else {
                        Toast.makeText(appContext, pleaseFillInTheFiledsText, Toast.LENGTH_SHORT).show()
                    }
                },
            ) {
                Text(text = stringResource(id = R.string.sign_up))
            }
        }

        var signUpCurrentStep = viewModel.currentStep.value

        Text(
            text = stringResource(id = R.string.step) + " $signUpCurrentStep / 3",
            color = TextWhiteColor,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
        )

        Button(
            onClick = {
                viewModel.currentStep.value--
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 25.dp, start = 15.dp)
        ) {
            Text(text = stringResource(id = R.string.back), color = TextWhiteColor)
        }
    }
    when(viewModel.appStateViewModel.uiState.value){
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
