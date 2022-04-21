package com.example.moviezone.model

import android.net.Uri

data class User (
    val id: String,
    val email: String,
    val password: String,
    val photoUri: Uri,
    val fullName: String,
    val username: String
    )