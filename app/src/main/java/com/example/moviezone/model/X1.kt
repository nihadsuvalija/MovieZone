package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class X1(
    @SerializedName("image_orientation")
    val imageOrientation: String,
    @SerializedName("medium")
    val medium: Medium,
    @SerializedName("region")
    val region: String
)