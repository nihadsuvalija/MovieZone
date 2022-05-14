package com.example.moviezone.screen.home

interface HomeViewModelInteractor {
    fun showNowPlayingMovies()
    fun showUpcomingMovies()
    fun showTopRatedMovies()
    fun showMovieDetails(movieId: Int, fromPage: Int)
    fun showDiscoverMovies()
    fun showFavoriteMovies()
}