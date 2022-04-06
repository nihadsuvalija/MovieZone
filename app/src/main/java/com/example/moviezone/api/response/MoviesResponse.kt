package com.example.moviezone.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("results")
    var movies: List<Movie>? = null
)