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
                viewInteractor?.setMoviePoster(Const.TMDB_IMAGE_URL + it.posterPath)
                viewInteractor?.setMovieTitle(it.title)
                viewInteractor?.setBackdrop(Const.TMDB_IMAGE_URL + it.backdropPath)
                viewInteractor?.setStoryLine(it.overview)
                viewInteractor?.setGenre(it.genres[0].name)
                viewInteractor?.setRuntime(it.runtime.toString() + " minutes")
                viewInteractor?.setRating(it.voteAverage.toString())
            }

            movieRepository.getMovieTrailerById(movieId).collect {
                viewInteractor?.setTrailer(it.results[0].key)
            }
        }
    }


}