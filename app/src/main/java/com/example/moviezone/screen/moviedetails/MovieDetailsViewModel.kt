package com.example.moviezone.screen.moviedetails

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.dao.DatabaseDAO
import com.example.moviezone.repository.MovieRepository
import com.example.moviezone.screen.youtubevideo.YoutubeActivity
import com.example.moviezone.utils.Const
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel: ViewModel(), MovieDetailsViewModelInteractor {
    private var viewInteractor: MovieDetailsViewInteractor? = null
    private var navController: NavController? = null
    private var trailer: String = ""
    private var movieRepository = MovieRepository()
    private var dao = DatabaseDAO()
    private var mAuth = FirebaseAuth.getInstance()

    val previousMovies: MutableList<Int> = mutableListOf()

    fun setViewInteractor(viewInteractor: MovieDetailsViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun launchYouTubeActivity(context: Context) {
        val intent = Intent(context, YoutubeActivity::class.java).apply {
            putExtra("Trailer", trailer)
        }
        context.startActivity(intent)
    }

    fun navigateBack(fromPage: Int){
        navController?.navigate(MovieDetailsFragmentDirections.navigateFromMovieDetailsToBase(fromPage))
    }

    fun addToFavorites(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getMovieById(movieId).collect {
                dao.addToFavorites(it, mAuth.currentUser?.uid.toString())
            }
        }
    }

    fun removeFromFavorites(movieId: Int) {
        dao.removeFromFavorites(movieId.toString(), mAuth.currentUser?.uid.toString())
    }

    fun getMovieById(movieId: Int) {
        
        viewInteractor?.scrollToTop()

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

    fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getSimilarMovies(movieId).collect {
                // TO DO: Update the similar movies adapter.
                viewInteractor?.setSimilarMovies(it.movies)
            }
        }
    }

    fun addMovieToBackStack(movieId: Int) {
        previousMovies.add(movieId)
    }

    fun removeMovieFromBackStack() {
        if (previousMovies.isNotEmpty()) {
            previousMovies.removeAt(previousMovies.size - 1);
        }
    }

    fun loadLastMovie(): Boolean {
        if (previousMovies.isNotEmpty()) {
            getMovieById(previousMovies[previousMovies.size - 1])
            getSimilarMovies(previousMovies[previousMovies.size - 1])
        }

        return previousMovies.isNotEmpty()
    }

    override fun showMovieDetails(movieId: Int) {
        addMovieToBackStack(movieId)
        getMovieById(movieId)
        getSimilarMovies(movieId)
    }

}