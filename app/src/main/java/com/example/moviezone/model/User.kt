package com.example.moviezone.model

import android.net.Uri

data class User (
    val id: String? = null,
    val email: String? = null,
    val password: String? = null,
    val photoUri: Uri? = null,
    val fullName: String? = null,
    val username: String? = null
    )