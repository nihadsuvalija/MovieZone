package com.example.moviezone.screen.profile

import com.example.moviezone.screen.base.BaseViewInteractor

interface ProfileViewInteractor: BaseViewInteractor {
    fun setProfilePicture(imagePath: String)
    fun setFullName(name: String)
    fun setEmail(email: String)
}