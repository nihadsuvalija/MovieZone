package com.example.moviezone.model

class FavoriteMovie(
    val id: Int,
    val posterPath: String,
    val voteAverage: Double,
) {
    constructor() : this (-1, "", -1.0)
}