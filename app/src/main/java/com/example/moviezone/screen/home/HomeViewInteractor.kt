package com.example.moviezone.screen.home

import android.net.Uri
import com.example.moviezone.model.FavoriteMovie
import com.example.moviezone.model.Movie
import com.example.moviezone.screen.base.BaseViewInteractor

interface HomeViewInteractor: BaseViewInteractor {
    fun setMovies(movies: List<Movie>)
    fun setProfilePhoto(photoUri: Uri?)
    fun setDiscoverMovies(movies: List<Movie>)
    fun setFavoriteMovies(movies: List<FavoriteMovie>)
}