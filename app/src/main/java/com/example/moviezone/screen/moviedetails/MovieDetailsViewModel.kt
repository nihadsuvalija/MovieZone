package com.example.moviezone.screen.moviedetails

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.model.DetailedMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel: ViewModel() {
    private var viewInteractor: MovieDetailsViewInteractor? = null
    private var navController: NavController? = null

    fun setViewInteractor(viewInteractor: MovieDetailsViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun getMovieById(movieId: Int) {
        RetrofitInstance.api.getMovieById(movieId).enqueue(object: Callback<DetailedMovie> {
            override fun onResponse(call: Call<DetailedMovie>, response: Response<DetailedMovie>) {
                println(response.body())
            }

            override fun onFailure(call: Call<DetailedMovie>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}