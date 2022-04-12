package com.example.moviezone.imdbmodel


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: String
)