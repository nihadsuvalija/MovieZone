package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class ReleaseDate(
    @SerializedName("notes")
    val notes: String,
    @SerializedName("release_date")
    val releaseDate: String
)