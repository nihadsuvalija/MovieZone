package com.example.moviezone.screen.sign_up

interface SignUpViewInteractor {
    fun setFullNameError(error: String)
    fun setEmailError(error: String)
    fun setPasswordError(error: String)
    fun clearInputFields()
}