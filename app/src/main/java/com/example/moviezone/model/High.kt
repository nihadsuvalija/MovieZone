package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class High(
    @SerializedName("film_trailer")
    val filmTrailer: String,
    @SerializedName("quality")
    val quality: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("trailer_image")
    val trailerImage: String,
    @SerializedName("version")
    val version: Int
)