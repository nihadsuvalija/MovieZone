package com.example.moviezone.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.FilmRepository
import com.example.moviezone.screen.base.BaseFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private var viewInteractor: HomeViewInteractor? = null
    private var navController: NavController? = null
    private var filmRepository = FilmRepository()

    fun setViewInteractor(viewInteractor: HomeViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onFilmClick(filmId: Int) {
        navController?.navigate(BaseFragmentDirections.navigateFromBaseToMovieDetails(filmId))
    }

    fun getNowShowing() {
        viewModelScope.launch {
            filmRepository.getNowShowing().collect {
                viewInteractor?.setFilms(it.films)
            }
        }
    }

    fun getComingSoon() {

    }
}