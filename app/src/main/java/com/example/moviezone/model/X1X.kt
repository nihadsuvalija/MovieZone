package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class X1X(
    @SerializedName("image_orientation")
    val imageOrientation: String,
    @SerializedName("medium")
    val medium: MediumX
)