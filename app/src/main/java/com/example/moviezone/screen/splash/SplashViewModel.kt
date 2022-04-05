package com.example.moviezone.screen.splash

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.R

class SplashViewModel: ViewModel(){
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun navigateToWelcome() {
        navController?.navigate(R.id.navigateFromSplashToWelcome)
    }

    fun navigateToBase() {
        navController?.navigate(R.id.navigateFromSplashToBase)
    }
}