package com.example.moviezone.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.IMDBMovieRepository
import com.example.moviezone.repository.TMDBMovieRepository
import com.example.moviezone.screen.base.BaseFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private var viewInteractor: HomeViewInteractor? = null
    private var navController: NavController? = null
    private var tmdbMovieRepository = TMDBMovieRepository()
    private var imdbMovieRepository = IMDBMovieRepository()

    fun setViewInteractor(viewInteractor: HomeViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onMovieClick(movieId: Int) {
        navController?.navigate(BaseFragmentDirections.navigateFromBaseToMovieDetails(movieId))
    }

    fun getTopRatedMovies(adapter: TopRatedMoviesAdapter) {
        viewModelScope.launch {
            tmdbMovieRepository.getTopRatedMovies().collect {
                it.movies?.let { it1 -> adapter.setTopRatedMovies(it1) }
            }
        }
    }

    fun getPopularMovies(adapter: PopularMoviesAdapter) {
        viewModelScope.launch {
            tmdbMovieRepository.getPopularMovies().collect {
                it.movies?.let { it1 -> adapter.setPopularMovies(it1) }
            }
        }
    }

    fun getInTheaters(adapter: InTheatersMoviesAdapter) {
        viewModelScope.launch {
            imdbMovieRepository.getInTheaters().collect {
                println(it.movies)
                adapter.setInTheatersMovies(it.movies)
            }
        }
    }
}