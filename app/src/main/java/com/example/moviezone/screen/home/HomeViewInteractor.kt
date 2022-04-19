package com.example.moviezone.screen.home

import android.net.Uri
import com.example.moviezone.model.Movie

interface HomeViewInteractor {
    fun setMovies(movies: List<Movie>)
    fun setProfilePhoto(photoUri: Uri?)
}