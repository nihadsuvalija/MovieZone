package com.example.moviezone.screen.sign_in

import com.example.moviezone.screen.ViewInteractor

interface SignInViewInteractor: ViewInteractor {
    fun setEmailError(error: String)
    fun setPasswordError(error: String)
    fun clearInputFields()
}