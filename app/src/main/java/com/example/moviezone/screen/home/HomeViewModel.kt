package com.example.moviezone.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.dao.DatabaseDAO
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.repository.MovieRepository
import com.example.moviezone.screen.central.CentralFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(), HomeViewModelInteractor {
    private var viewInteractor: HomeViewInteractor? = null
    private var navController: NavController? = null
    private var movieRepository = MovieRepository()
    private var mAuth = FirebaseAuth.getInstance()
    private var dao = DatabaseDAO()

    fun setViewInteractor(viewInteractor: HomeViewInteractor) {
        this.viewInteractor = viewInteractor
        dao.setHomeViewInteractor(viewInteractor)
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    private fun onMovieClick(movieId: Int, fromPage: Int) {
        navController?.navigate(CentralFragmentDirections.navigateFromBaseToMovieDetails(movieId, fromPage))
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

    private fun getFavoriteMovies() {
        dao.getFavorites(mAuth.currentUser?.uid.toString())
    }

    fun setProfilePhoto() {
        dao.setProfilePhoto(mAuth.currentUser?.uid.toString())
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

    override fun showFavoriteMovies() {
        getFavoriteMovies()
    }
}