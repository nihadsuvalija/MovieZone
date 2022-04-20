package com.example.moviezone.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.MovieRepository
import com.example.moviezone.screen.base.BaseFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(), HomeViewModelInteractor {
    private var viewInteractor: HomeViewInteractor? = null
    private var navController: NavController? = null
    private var movieRepository = MovieRepository()
    private var mAuth = FirebaseAuth.getInstance()

    fun setViewInteractor(viewInteractor: HomeViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    private fun onMovieClick(movieId: Int, fromPage: Int) {
        navController?.navigate(BaseFragmentDirections.navigateFromBaseToMovieDetails(movieId, fromPage))
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            movieRepository.getUpcomingMovies().collect {
                viewInteractor?.setMovies(listOf())
                viewInteractor?.setMovies(it.movies)
            }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovies().collect {
                viewInteractor?.setMovies(listOf())
                viewInteractor?.setMovies(it.movies)
            }
        }
    }

    private fun getDiscoverMovies() {
        viewModelScope.launch {
            movieRepository.getDiscoverMovies().collect {
                viewInteractor?.setDiscoverMovies(it.movies)
            }
        }
    }

    fun setProfilePhoto() {
        viewInteractor?.setProfilePhoto(mAuth.currentUser?.photoUrl)
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            movieRepository.getTopRatedMovies().collect {
                viewInteractor?.setMovies(listOf())
                viewInteractor?.setMovies(it.movies)
            }
        }
    }

    override fun showNowPlayingMovies() {
        getNowPlayingMovies()
    }

    override fun showUpcomingMovies() {
        getUpcomingMovies()
    }

    override fun showTopRatedMovies() {
        getTopRatedMovies()
    }

    override fun showMovieDetails(movieId: Int, fromPage: Int) {
        onMovieClick(movieId, fromPage)
    }

    override fun showDiscoverMovies() {
        getDiscoverMovies()
    }
}