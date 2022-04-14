package com.example.moviezone.api.response


import com.example.moviezone.model.Film
import com.example.moviezone.model.Status
import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("films")
    val films: List<Film>,
    @SerializedName("status")
    val status: Status
)