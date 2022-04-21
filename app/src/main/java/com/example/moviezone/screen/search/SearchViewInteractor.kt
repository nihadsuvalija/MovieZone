package com.example.moviezone.screen.search

import com.example.moviezone.model.SearchedMovie
import com.example.moviezone.screen.ViewInteractor

interface SearchViewInteractor: ViewInteractor {
    fun setMovies(movies: List<SearchedMovie>)
    fun setNoResults()

}