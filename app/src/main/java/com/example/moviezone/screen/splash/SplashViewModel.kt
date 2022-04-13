package com.example.moviezone.screen.splash

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.model.CurrentUser
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel: ViewModel(){
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun navigateToWelcome() {
        navController?.navigate(SplashFragmentDirections.navigateFromSplashToWelcome())
    }

    fun navigateToBase() {
        navController?.navigate(SplashFragmentDirections.navigateFromSplashToBase())
    }

    fun updateCurrentUser() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            CurrentUser.update(
                FirebaseAuth.getInstance().currentUser?.uid.toString(),
                FirebaseAuth.getInstance().currentUser?.displayName.toString(),
                FirebaseAuth.getInstance().currentUser?.email.toString(),
                FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
            )
        }
    }

}