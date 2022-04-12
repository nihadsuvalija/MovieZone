package com.example.moviezone.imdbmodel


import com.google.gson.annotations.SerializedName

data class Similar(
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
)