package com.example.moviezone.screen.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.FilmRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel: ViewModel() {
    private var viewInteractor: MovieDetailsViewInteractor? = null
    private var navController: NavController? = null
    private var movieRepository = FilmRepository()

    fun setViewInteractor(viewInteractor: MovieDetailsViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun navigateBack(){
        navController?.navigate(MovieDetailsFragmentDirections.navigateFromMovieDetailsToBase())
    }
}