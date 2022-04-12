package com.example.moviezone.screen.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.MovieRepository
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

    fun getMovieById(movieId: String) {
        viewModelScope.launch {
            movieRepository.getMovieById(movieId).collect {
                it.let {
                    println(it.title)
                    viewInteractor?.setMovieTitle(it.title)
                    viewInteractor?.setMoviePoster(it.image)
                    viewInteractor?.setReleaseDate(it.releaseDate)
                    viewInteractor?.setRuntime(it.runtimeMins + " minutes")
                    viewInteractor?.setBackdrop(it.image)
                    viewInteractor?.setGenre(it.genreList[0].value)
                    if (it.imDbRating.isNotBlank()) {
                        viewInteractor?.setRating(it.imDbRating)
                    } else {
                        viewInteractor?.setRating(0.toString())
                    }
                    viewInteractor?.setStoryLine(it.plot)
                }
            }
        }
    }
/*
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

 */
}