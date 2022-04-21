package com.example.moviezone.screen.sign_up

import com.example.moviezone.screen.base.BaseViewInteractor

interface SignUpViewInteractor: BaseViewInteractor {
    fun setFullNameError(error: String)
    fun setEmailError(error: String)
    fun setPasswordError(error: String)
    fun clearInputFields()
}