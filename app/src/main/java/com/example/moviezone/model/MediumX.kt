package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class MediumX(
    @SerializedName("film_image")
    val filmImage: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int
)