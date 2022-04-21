package com.example.moviezone.screen.sign_up

import com.example.moviezone.screen.ViewInteractor

interface SignUpViewInteractor: ViewInteractor {
    fun setFullNameError(error: String)
    fun setEmailError(error: String)
    fun setPasswordError(error: String)
    fun clearInputFields()
}