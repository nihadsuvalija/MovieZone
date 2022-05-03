package com.example.moviezone.screen.moviedetails

import com.example.moviezone.model.Cast
import com.example.moviezone.model.Movie
import com.example.moviezone.model.Review
import com.example.moviezone.screen.base.BaseViewInteractor

interface MovieDetailsViewInteractor: BaseViewInteractor {
    fun setMoviePoster(path: String)
    fun setMovieTitle(title: String)
    fun setReleaseDate(date: String)
    fun setRuntime(runtime: String)
    fun setGenre(genre: String)
    fun setBackdrop(path: String)
    fun setRating(rating: String)
    fun setStoryLine(storyLine: String)
    fun setReviews(reviews: List<Review>)
    fun setCast(cast: List<Cast>)
    fun setTrailer(exists: Boolean)
    fun setSimilarMovies(movies: List<Movie>)
    fun scrollToTop()
}