package com.example.moviezone.api

import com.example.moviezone.model.DetailedMovie
import com.example.moviezone.model.MoviesResponse
import com.example.moviezone.utils.Const
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBApi {
    // TODO: Write the API requests that need to be made to the API

    @GET("movie/top_rated?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getTopRatedMovies(): MoviesResponse

    @GET("movie/popular?api_key=${Const.TMDB_API_KEY}&language=en-US&")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("movie/{movie_id}?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getMovieById(@Path("movie_id") id: Int): DetailedMovie
}