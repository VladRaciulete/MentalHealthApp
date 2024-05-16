package com.example.mentalhealth.presentation.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    val auth: FirebaseAuth
) : ViewModel() {
    var emailAddress = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)

    var emailAddressShowError = mutableStateOf(false)
    var passwordShowError = mutableStateOf(false)

    fun logIn(){
        auth.signInWithEmailAndPassword(emailAddress.value,password.value).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d("TAG","User logged in successfully!")
                println("User logged in successfully!")
            }
            else {
                Log.d("TAG","There was an an error while logging in!")
            }
        }
    }
}