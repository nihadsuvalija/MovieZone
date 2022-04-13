package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("genre_id")
    val genreId: Int,
    @SerializedName("genre_name")
    val genreName: String
)