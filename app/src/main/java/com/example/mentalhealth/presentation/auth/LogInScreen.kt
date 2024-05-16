package com.example.mentalhealth.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mentalhealth.R
import com.example.mentalhealth.Validator
import com.example.mentalhealth.navigation.Screen
import com.example.mentalhealth.ui.theme.*

@Composable
fun LogInScreen(navController: NavController, viewModel: LogInViewModel){
    var appContext = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Text(text = stringResource(id = R.string.welcome_back),
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(40.dp))

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

        var passwordVisible by remember {
            mutableStateOf(false)
        }
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
                keyboardType = KeyboardType.Password
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

        Spacer(modifier = Modifier.height(15.dp))

        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.forgot_password)),
            style = UnderlinedLinkTextStyle
        ) {
            //Handle Forgot Password click
            navController.navigate(Screen.ForgotPasswordScreen.route)
        }

        Spacer(modifier = Modifier.height(15.dp))

        var pleaseFillInTheFiledsText = stringResource(id = R.string.please_fill_in_the_fields)

        Button(
            onClick = {
                if (Validator.validateLogInFields(viewModel.emailAddress.value, viewModel.password.value)){
                    viewModel.logIn()
                    navController.navigate("journal") {
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                }
                else {
                    Toast.makeText(appContext, pleaseFillInTheFiledsText,Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentColor,
            )
        ) {
            Text(text = stringResource(id = R.string.log_in))
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.no_account),
                color = TextWhiteColor,
                fontSize = 16.sp,
            )
            Text(text = " ")
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.sign_up)),
                style = UnderlinedLinkTextStyle
            ) {
                navController.navigate(Screen.SignUpScreenStep1.route)
            }
        }
    }
}
