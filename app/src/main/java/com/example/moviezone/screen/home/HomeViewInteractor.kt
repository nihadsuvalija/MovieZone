package com.example.moviezone.screen.home

import com.example.moviezone.model.Movie

interface HomeViewInteractor {
    fun setMovies(movies: List<Movie>)
}