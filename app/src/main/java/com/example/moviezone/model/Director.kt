package com.example.moviezone.model


import com.google.gson.annotations.SerializedName

data class Director(
    @SerializedName("director_id")
    val directorId: Int,
    @SerializedName("director_name")
    val directorName: String
)