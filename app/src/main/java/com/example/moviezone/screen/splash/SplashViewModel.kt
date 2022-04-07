package com.example.moviezone.screen.splash

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.R
import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.model.Movie
import com.example.moviezone.model.MoviesResponse
import com.example.moviezone.screen.home.TopRatedMoviesAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                FirebaseAuth.getInstance().currentUser?.displayName.toString(),
                FirebaseAuth.getInstance().currentUser?.email.toString()
            )
        }
    }

}