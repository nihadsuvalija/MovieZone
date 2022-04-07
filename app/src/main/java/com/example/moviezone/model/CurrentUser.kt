package com.example.moviezone.model

object CurrentUser {
    var fullName: String = "";
    var email: String = "";

    fun update(fullName: String, email: String) {
        this.fullName = fullName
        this.email = email
    }
}