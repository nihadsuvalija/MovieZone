package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Trailers(
    @SerializedName("high")
    val high: List<High>
)