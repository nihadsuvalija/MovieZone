package com.example.moviezone.screen.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class HomeViewModel: ViewModel() {
    private var viewInteractor: HomeViewInteractor? = null
    private var navController: NavController? = null

    fun setViewInteractor(viewInteractor: HomeViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }
}