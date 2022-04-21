package com.example.moviezone.screen.profile

import com.example.moviezone.screen.ViewInteractor

interface ProfileViewInteractor: ViewInteractor {
    fun setProfilePicture(imagePath: String)
    fun setFullName(name: String)
    fun setEmail(email: String)
}