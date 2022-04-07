package com.example.moviezone.screen.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.R
import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.model.MoviesResponse
import com.example.moviezone.screen.base.BaseFragmentDirections
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private var viewInteractor: HomeViewInteractor? = null
    private var navController: NavController? = null

    fun setViewInteractor(viewInteractor: HomeViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onMovieClick(movieId: Int) {
        navController?.navigate(BaseFragmentDirections.navigateFromBaseToMovieDetails(movieId))
    }

    fun getTopRatedMovies(adapter: TopRatedMoviesAdapter) {
        RetrofitInstance.api.getTopRatedMovies().enqueue(object: Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.movies?.let { adapter.setTopRatedMovies(it) }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getPopularMovies(adapter: PopularMoviesAdapter) {
        RetrofitInstance.api.getPopularMovies().enqueue(object: Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.movies?.let { adapter.setPopularMovies(it) }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}