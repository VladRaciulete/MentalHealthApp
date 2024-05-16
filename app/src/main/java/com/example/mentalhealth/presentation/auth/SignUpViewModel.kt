package com.example.mentalhealth.presentation.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val auth: FirebaseAuth
) : ViewModel() {
    init {
    }
    //AICI Modify elements to
    //var x by remember { mutableStateOf() }

    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var emailAddress = mutableStateOf("")
    var password = mutableStateOf("")
    //var confirmPassword = mutableStateOf("") //AICI (DE IMPLEMENTAT)
    var passwordVisible = mutableStateOf(false)

    var birthDate = mutableStateOf("")
    var gender = mutableStateOf("")
    var profession = mutableStateOf("")
    var occupation = mutableStateOf("")

    var maritalStatus = mutableStateOf("")
    var livingArea = mutableStateOf("")
    var publicFigure = mutableStateOf("")//AICI (DE MODIFICAT LA BOOLEAN)

    var currentStep = mutableStateOf(1)

    var firstNameShowError = mutableStateOf(false)
    var lastNameShowError = mutableStateOf(false)
    var emailAddressShowError = mutableStateOf(false)
    var passwordShowError = mutableStateOf(false)
    //var confirmPasswordShowError = mutableStateOf(false)

    var birthDateShowError = mutableStateOf(false)
    var genderShowError = mutableStateOf(false)
    var professionShowError = mutableStateOf(false)
    var occupationShowError = mutableStateOf(false)

    var maritalStatusShowError = mutableStateOf(false)
    var livingAreaShowError = mutableStateOf(false)
    var publicFigureShowError = mutableStateOf(false)


    val genderList = listOf("Male","Female","Prefer not to say")
    val maritalStatusList = listOf("Married","Single","Divorced")
    val livingAreaList = listOf("Rural","Urban")
    val publicFigureList = listOf("Yes","No")

    fun signUp(){
        auth.createUserWithEmailAndPassword(emailAddress.value, password.value).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d("TAG","Account Created Successfully!")
                println("Account Created Successfully!")
            }
            else {
                Log.d("TAG","There was an an error while creating the account!")
            }
        }
    }
}