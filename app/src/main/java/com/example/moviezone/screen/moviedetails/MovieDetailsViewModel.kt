package com.example.moviezone.screen.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.MovieRepository
import com.example.moviezone.utils.Const
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel: ViewModel() {
    private var viewInteractor: MovieDetailsViewInteractor? = null
    private var navController: NavController? = null
    private var movieRepository = MovieRepository()

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
        viewModelScope.launch {
            movieRepository.getMovieById(movieId).collect {
                it.let {
                    viewInteractor?.setMovieTitle(it.originalTitle)
                    viewInteractor?.setMoviePoster(Const.TMDB_IMAGE_URL + it.posterPath)
                    viewInteractor?.setReleaseDate(it.releaseDate)
                    viewInteractor?.setRuntime(it.runtime.toString() + " minutes")
                    viewInteractor?.setBackdrop(Const.TMDB_IMAGE_URL + it.backdropPath)
                    viewInteractor?.setGenre(it.genres[0].name.toString())
                    viewInteractor?.setRating(it.voteAverage.toString())
                    viewInteractor?.setStoryLine(it.overview)
                }
            }
        }
    }

    fun getCreditsByMovieId(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getCreditsByMovieId(movieId).collect {
                // Set the cast and crew of movies
                viewInteractor?.setMovieCredits(it)
            }
        }
    }

    fun getReviewsByMovieId(movieId: Int) {
        viewModelScope.launch {
            movieRepository.getReviewsByMovieId(movieId).collect {
                viewInteractor?.setMovieReviews(it.reviews)
            }
        }
    }
}