package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class X2(
    @SerializedName("image_orientation")
    val imageOrientation: String,
    @SerializedName("medium")
    val medium: MediumXXX,
    @SerializedName("region")
    val region: String
)