package com.example.moviezone.model

import android.net.Uri

object CurrentUser {
    var id = ""
    var fullName = ""
    var email = ""
    var image = ""
    var signedIn = false

    fun update(id: String, fullName: String, email: String, image: String) {
        this.id = id
        this.fullName = fullName
        this.email = email
        this.image = image
    }

}