package com.example.moviezone.model

object CurrentUser {
    var id = ""
    var fullName = ""
    var email = ""
    var image = ""

    fun update(id: String, fullName: String, email: String, image: String) {
        this.id = id
        this.fullName = fullName
        this.email = email
        this.image = image
    }

    fun signedIn(): Boolean {
        if (id == "") return false
        return true
    }
}