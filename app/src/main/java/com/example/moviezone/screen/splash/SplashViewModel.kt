package com.example.moviezone.screen.splash

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.model.CurrentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashViewModel: ViewModel() {
    private var navController: NavController? = null
    private var mAuth = FirebaseAuth.getInstance()

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
                mAuth.currentUser?.uid.toString(),
                mAuth.currentUser?.displayName.toString(),
                mAuth.currentUser?.email.toString(),
                mAuth.currentUser?.photoUrl.toString()
            )
        }
    }

    fun isLogged(): Boolean {
        if (Firebase.auth.currentUser?.uid != null) return true
        return false
    }
}

