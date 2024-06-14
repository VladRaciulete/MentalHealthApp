package com.example.mentalhealth.domain.model

data class User(
    var firstName: String = "",
    var lastName: String = "",
    var birthDate: String = "",
    var gender: String = "",
    var studies: String = "",
    var occupation: String = "",
    var maritalStatus: String = "",
    var livingArea: String = "",
    var publicFigure: String = "",
)
