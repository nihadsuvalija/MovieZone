package com.example.moviezone.screen.moviedetails

import com.example.moviezone.model.MovieCredits

interface MovieDetailsViewInteractor {
    fun setMoviePoster(path: String)
    fun setMovieTitle(title: String)
    fun setReleaseDate(date: String)
    fun setRuntime(runtime: String)
    fun setGenre(genre: String)
    fun setBackdrop(path: String)
    fun setRating(rating: String)
    fun setStoryLine(storyLine: String)
    fun setMovieCredits(credits: MovieCredits)
}