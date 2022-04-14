package com.example.moviezone.screen.moviedetails

import com.example.moviezone.model.Cast

interface MovieDetailsViewInteractor {
    fun setMoviePoster(path: String)
    fun setMovieTitle(title: String)
    fun setReleaseDate(date: String)
    fun setRuntime(runtime: String)
    fun setGenre(genre: String)
    fun setBackdrop(path: String)
    fun setRating(rating: String)
    fun setStoryLine(storyLine: String)
    fun setTrailer(trailer: String)
    fun setCast(cast: List<Cast>)
}