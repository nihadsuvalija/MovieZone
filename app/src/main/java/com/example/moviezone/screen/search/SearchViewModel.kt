package com.example.moviezone.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.MovieRepository
import com.example.moviezone.screen.central.CentralFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel(), SearchViewModelInteractor{
    private var navController: NavController? = null
    private var viewInteractor: SearchViewInteractor? = null
    private var movieRepository = MovieRepository()

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun setViewInteractor(viewInteractor: SearchViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun searchByTitle(title: String) {
        viewModelScope.launch {
            movieRepository.searchMoviesByTitle(title).collect {
                viewInteractor?.setMovies(it.movies)
            }
        }
    }

    private fun onMovieClick(movieId: Int, fromPage: Int) {
        navController?.navigate(CentralFragmentDirections.navigateFromBaseToMovieDetails(movieId, fromPage))
    }

    override fun showMovieDetails(movieId: Int, fromPage: Int) {
        onMovieClick(movieId, fromPage)
    }
}