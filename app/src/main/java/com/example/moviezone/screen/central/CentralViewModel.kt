package com.example.moviezone.screen.central

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class CentralViewModel: ViewModel() {

    private var viewInteractor: CentralViewInteractor? = null
    private var navController: NavController? = null

    fun setViewInteractor(viewInteractor: CentralViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }
}