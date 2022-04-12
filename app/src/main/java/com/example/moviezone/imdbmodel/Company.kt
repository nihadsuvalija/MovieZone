package com.example.moviezone.imdbmodel


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)