package com.example.moviezone.screen.home

import com.example.moviezone.model.Film

interface HomeViewInteractor {
    fun setFilms(films: List<Film>)
}