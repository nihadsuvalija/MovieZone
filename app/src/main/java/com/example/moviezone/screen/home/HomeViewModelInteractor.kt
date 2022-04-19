package com.example.moviezone.screen.home

interface HomeViewModelInteractor {
    fun showNowPlayingMovies()
    fun showUpcomingMovies()
    fun showFavoriteMovies()
    fun showMovieDetails(movieId: Int)
}