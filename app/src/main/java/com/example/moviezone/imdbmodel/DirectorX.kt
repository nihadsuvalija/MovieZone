package com.example.moviezone.imdbmodel


import com.google.gson.annotations.SerializedName

data class DirectorX(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)