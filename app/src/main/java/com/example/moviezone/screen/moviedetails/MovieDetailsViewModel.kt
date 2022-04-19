package com.example.moviezone.screen.moviedetails

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.MovieRepository
import com.example.moviezone.screen.youtubevideo.YoutubeActivity
import com.example.moviezone.utils.Const
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel: ViewModel() {
    private var viewInteractor: MovieDetailsViewInteractor? = null
    private var navController: NavController? = null
    private var trailer: String = ""
    private var movieRepository = MovieRepository()

    fun setViewInteractor(viewInteractor: MovieDetailsViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun launchYouTubeActivity(context: Context) {
        var intent = Intent(context, YoutubeActivity::class.java).apply {
            putExtra("Trailer", trailer)
        }
        context.startActivity(intent)
    }

    fun navigateBack(fromPage: Int){
        navController?.navigate(MovieDetailsFragmentDirections.navigateFromMovieDetailsToBase(fromPage))
    }

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            try {
                movieRepository.getMovieById(movieId).collect {
                    viewInteractor?.setMoviePoster(Const.TMDB_IMAGE_URL + it.posterPath)
                    viewInteractor?.setMovieTitle(it.title)
                    viewInteractor?.setBackdrop(Const.TMDB_IMAGE_URL + it.backdropPath)
                    viewInteractor?.setStoryLine(it.overview)
                    viewInteractor?.setGenre(it.genres[0].name)
                    viewInteractor?.setRuntime(it.runtime.toString() + " minutes")
                    viewInteractor?.setRating(it.voteAverage.toString())
                    viewInteractor?.setReleaseDate(it.releaseDate)
                }

                movieRepository.getMovieTrailerById(movieId).collect {
                    trailer = it.results[0].key
                }

                movieRepository.getCreditsByMovieId(movieId).collect {
                    viewInteractor?.setCast(it.cast)
                }

                movieRepository.getReviewsById(movieId).collect {
                    viewInteractor?.setReviews(it.reviews)
                }
            } catch (e: Exception) {
                Log.i("Error", e.message.toString())
            }
        }
    }

}