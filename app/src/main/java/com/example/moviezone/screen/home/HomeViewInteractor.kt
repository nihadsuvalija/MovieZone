package com.example.moviezone.screen.home

import android.net.Uri
import com.example.moviezone.model.Movie
import com.example.moviezone.screen.ViewInteractor

interface HomeViewInteractor: ViewInteractor {
    fun setMovies(movies: List<Movie>)
    fun setProfilePhoto(photoUri: Uri?)
    fun setDiscoverMovies(movies: List<Movie>)
}