package com.example.moviezone.screen.profile

import android.content.Intent
import com.example.moviezone.screen.base.BaseViewInteractor

interface ProfileViewInteractor: BaseViewInteractor {
    fun setProfilePhoto(imagePath: String)
    fun changeProfilePhoto(intent: Intent)
    fun setFullName(name: String)
    fun setEmail(email: String)
}