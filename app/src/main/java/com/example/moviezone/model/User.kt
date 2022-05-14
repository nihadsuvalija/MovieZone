package com.example.moviezone.model

import android.net.Uri

class User (
    val id: String? = null,
    var email: String? = null,
    var password: String? = null,
    var photoPath: String? = null,
    var fullName: String? = null,
    var username: String? = null
) {
    constructor() : this(null, null, null, null, null, null)
}