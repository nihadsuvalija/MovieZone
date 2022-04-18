package com.example.moviezone.screen.search

import com.example.moviezone.model.SearchedMovie

interface SearchViewInteractor {
    fun setMovies(movies: List<SearchedMovie>)
    fun setNoResults()
}