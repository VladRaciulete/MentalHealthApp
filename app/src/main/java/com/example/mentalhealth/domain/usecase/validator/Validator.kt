package com.example.mentalhealth.domain.usecase.validator

object Validator {
    fun validateFirstName(firstName: String) : Boolean {
        return (firstName.isNotEmpty() && firstName.length > 1)
    }

    fun validateLastName(lastName: String) : Boolean {
        return (lastName.isNotEmpty() && lastName.length > 1)
    }

    fun validateEmailAddress(emailAddress: String) : Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
        return emailAddress.matches(emailPattern)
    }

    fun validatePassword(password: String) : Boolean {
        return (password.isNotEmpty() && password.length > 5)
    }

    fun validateDate(date: String) : Boolean {
        //AICI To implement
        return true
    }

    fun validateGender(gender: String) : Boolean {
        return gender.isNotEmpty()
    }

    fun validateProfession(profession: String) : Boolean {
        return profession.isNotEmpty()
    }

    fun validateOccupation(occupation: String) : Boolean {
        return occupation.isNotEmpty()
    }

    fun validateMaritalStatus(maritalStatus: String) : Boolean {
        return maritalStatus.isNotEmpty()
    }

    fun validateLivingArea(livingArea: String) : Boolean {
        return livingArea.isNotEmpty()
    }

    fun validatePublicFigure(publicFigure: String) : Boolean {
        return publicFigure.isNotEmpty()
    }

    fun validateSignUpScreen1(
        firstName: String,
        lastName: String,
        emailAddress: String,
        password: String
    ) : Boolean {
        return  validateFirstName(firstName) &&
                validateLastName(lastName) &&
                validateEmailAddress(emailAddress) &&
                validatePassword(password)
    }

    fun validateSignUpScreen2(
        date: String,
        gender: String,
        profession: String,
        occupation: String
    ) : Boolean {
        return  validateDate(date) &&
                validateGender(gender) &&
                validateProfession(profession) &&
                validateOccupation(occupation)
    }

    fun validateSignUpScreen3(
        maritalStatus: String,
        livingArea: String,
        publicFigure: String
    ) : Boolean {
        return  validateMaritalStatus(maritalStatus) &&
                validateLivingArea(livingArea) &&
                validatePublicFigure(publicFigure)
    }

    fun validateSignUpFields(
        firstName: String,
        lastName: String,
        emailAddress: String,
        password: String,
        date: String,
        gender: String,
        profession: String,
        occupation: String,
        maritalStatus: String,
        livingArea: String,
        publicFigure: String
    ) : Boolean {
        return  validateFirstName(firstName) &&
                validateLastName(lastName) &&
                validateEmailAddress(emailAddress) &&
                validatePassword(password) &&
                validateDate(date) &&
                validateGender(gender) &&
                validateProfession(profession) &&
                validateOccupation(occupation) &&
                validateMaritalStatus(maritalStatus) &&
                validateLivingArea(livingArea) &&
                validatePublicFigure(publicFigure)
    }

    fun validateLogInFields(emailAddress: String, password: String,) : Boolean {
        return validateEmailAddress(emailAddress) && validatePassword(password)
    }
}