package com.example.moviezone.screen.sign_in

import com.example.moviezone.screen.base.BaseViewInteractor

interface SignInViewInteractor: BaseViewInteractor {
    fun setEmailError(error: String)
    fun setPasswordError(error: String)
    fun clearInputFields()
}