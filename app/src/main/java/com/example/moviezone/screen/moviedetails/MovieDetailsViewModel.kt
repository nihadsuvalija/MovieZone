package com.example.moviezone.screen.moviedetails

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.model.DetailedMovie
import com.example.moviezone.utils.Const
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

    fun navigateBack(){
        navController?.navigate(MovieDetailsFragmentDirections.navigateFromMovieDetailsToBase())
    }

    fun getMovieById(movieId: Int) {
        RetrofitInstance.api.getMovieById(movieId).enqueue(object: Callback<DetailedMovie> {
            override fun onResponse(call: Call<DetailedMovie>, response: Response<DetailedMovie>) {
                println(response.body())
                response.body()?.let {
                    viewInteractor?.setMovieTitle(it.originalTitle)
                    viewInteractor?.setMoviePoster(Const.TMDB_IMAGE_URL + it.posterPath)
                    viewInteractor?.setReleaseDate(it.releaseDate)
                    viewInteractor?.setRuntime(it.runtime.toString() + " minutes")
                    viewInteractor?.setBackdrop(Const.TMDB_IMAGE_URL + it.backdropPath)
                    viewInteractor?.setGenre(it.genres[0].name.toString())
                }
            }

            override fun onFailure(call: Call<DetailedMovie>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}