package com.example.moviezone.screen.profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class ProfileViewModel: ViewModel() {
    private var navController: NavController? = null
    private var viewInteractor: ProfileViewInteractor? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun setViewInteractor(viewInteractor: ProfileViewInteractor) {
        this.viewInteractor = viewInteractor
    }
}