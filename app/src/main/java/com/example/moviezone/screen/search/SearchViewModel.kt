package com.example.moviezone.screen.search

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class SearchViewModel: ViewModel() {
    private var navController: NavController? = null
    private var viewInteractor: SearchViewInteractor? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun setViewInteractor(viewInteractor: SearchViewInteractor) {
        this.viewInteractor = viewInteractor
    }
}