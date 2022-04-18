package com.example.moviezone.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.MovieRepository
import com.example.moviezone.screen.base.BaseFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(), HomeViewModelInteractor {
    private var viewInteractor: HomeViewInteractor? = null
    private var navController: NavController? = null
    private var movieRepository = MovieRepository()

    fun setViewInteractor(viewInteractor: HomeViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onMovieClick(movieId: Int) {
        navController?.navigate(BaseFragmentDirections.navigateFromBaseToMovieDetails(movieId))
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            movieRepository.getUpcomingMovies().collect {
                viewInteractor?.setMovies(listOf())
                viewInteractor?.setMovies(it.movies)
            }
        }
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovies().collect {
                viewInteractor?.setMovies(listOf())
                viewInteractor?.setMovies(it.movies)
            }
        }
    }

    fun getFavoriteMovies() {
        viewInteractor?.setMovies(listOf())
    }

    override fun showNowPlayingMovies() {
        getNowPlayingMovies()
    }

    override fun showUpcomingMovies() {
        getUpcomingMovies()
    }

    override fun showFavoriteMovies() {
        getFavoriteMovies()
    }
}