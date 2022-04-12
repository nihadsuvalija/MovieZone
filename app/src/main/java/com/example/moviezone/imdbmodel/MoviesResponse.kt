package com.example.moviezone.imdbmodel


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("items")
    val movies: List<Movie>
)