package com.example.moviezone.api

import com.example.moviezone.model.Genre
import com.example.moviezone.model.MoviesResponse
import com.example.moviezone.utils.Const
import retrofit2.Call
import retrofit2.http.GET

interface TMDBApi {
    // TODO: Write the API requests that need to be made to the API
    // Example of a request: https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher -- THIS IS FOR SEARCH

    @GET("movie/top_rated?api_key=${Const.TMDB_API_KEY}&language=en-US")
    fun getTopRatedMovies(): Call<MoviesResponse>

    @GET("genre/movie/list?api_key=${Const.TMDB_API_KEY}&language=en-US")
    fun getAllGenres(): Call<List<Genre>>
}