package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("cast_id")
    val castId: Int,
    @SerializedName("cast_name")
    val castName: String
)