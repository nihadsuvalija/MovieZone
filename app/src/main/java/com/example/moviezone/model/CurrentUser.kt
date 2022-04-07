package com.example.moviezone.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

object CurrentUser {
    var fullName: String = "";
    var email: String = "";

    fun update(fullName: String, email: String) {
        this.fullName = fullName
        this.email = email
    }
}