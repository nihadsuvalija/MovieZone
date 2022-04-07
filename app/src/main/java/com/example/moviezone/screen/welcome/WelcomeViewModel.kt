package com.example.moviezone.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.R

class WelcomeViewModel: ViewModel() {

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onSignUpClicked() {
        navController?.navigate(WelcomeFragmentDirections.navigateFromWelcomeToSignUp())
    }

    fun onSignInClicked() {
        navController?.navigate(WelcomeFragmentDirections.navigateFromWelcomeToSignIn())
    }
}