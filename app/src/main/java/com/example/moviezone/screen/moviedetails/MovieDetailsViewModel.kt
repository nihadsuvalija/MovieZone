package com.example.moviezone.screen.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviezone.repository.FilmRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel: ViewModel() {
    private var viewInteractor: MovieDetailsViewInteractor? = null
    private var navController: NavController? = null
    private var filmRepository = FilmRepository()

    fun setViewInteractor(viewInteractor: MovieDetailsViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun navigateBack(){
        navController?.navigate(MovieDetailsFragmentDirections.navigateFromMovieDetailsToBase())
    }

    fun getFilmById(filmId: Int) {
        viewModelScope.launch {
            filmRepository.getFilmById(filmId).collect {
                viewInteractor?.setMoviePoster(it.images.poster.x1.medium.filmImage)
                viewInteractor?.setMovieTitle(it.filmName)
                viewInteractor?.setRating(it.reviewStars.toString())
                viewInteractor?.setReleaseDate(it.releaseDates[0].releaseDate)
                viewInteractor?.setGenre(it.genres[0].genreName)
                viewInteractor?.setStoryLine(it.synopsisLong)
                viewInteractor?.setBackdrop(it.images.poster.x1.medium.filmImage)
                viewInteractor?.setCast(it.cast)
                println(it.trailers != null)
                if (it.trailers != null) viewInteractor?.setTrailer(it.trailers.high[0].filmTrailer) else viewInteractor?.setTrailer("")
            }
        }
    }
}