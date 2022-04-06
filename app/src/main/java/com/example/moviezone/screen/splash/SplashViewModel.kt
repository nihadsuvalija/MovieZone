package com.example.moviezone.screen.splash

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.R
import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.model.Movie
import com.example.moviezone.model.MoviesResponse
import com.example.moviezone.screen.home.TopRatedMoviesAdapter
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
        navController?.navigate(R.id.navigateFromSplashToWelcome)
    }

    fun navigateToBase() {
        navController?.navigate(R.id.navigateFromSplashToBase)
    }

}