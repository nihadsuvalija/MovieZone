package com.example.moviezone.screen.sign_in

interface SignInViewInteractor {
    fun setEmailError(error: String)
    fun setPasswordError(error: String)
    fun clearInputFields()
    fun displayPopUp(message: String)
}