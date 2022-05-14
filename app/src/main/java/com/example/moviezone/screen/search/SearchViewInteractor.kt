package com.example.moviezone.screen.search

import com.example.moviezone.model.SearchedMovie
import com.example.moviezone.screen.base.BaseViewInteractor

interface SearchViewInteractor: BaseViewInteractor {
    fun setMovies(movies: List<SearchedMovie>)
    fun setNoResults()

}