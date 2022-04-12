package com.example.moviezone.model

object CurrentUser {
    var fullName = ""
    var email = ""

    fun update(fullName: String, email: String) {
        this.fullName = fullName
        this.email = email
    }
}