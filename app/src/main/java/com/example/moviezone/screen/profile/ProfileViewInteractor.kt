package com.example.moviezone.screen.profile

interface ProfileViewInteractor {
    fun setProfilePicture(imagePath: String)
    fun setFullName(name: String)
    fun setEmail(email: String)
}