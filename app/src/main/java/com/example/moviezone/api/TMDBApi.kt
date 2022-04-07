package com.example.moviezone.api

import com.example.moviezone.model.DetailedMovie
import com.example.moviezone.model.MoviesResponse
import com.example.moviezone.utils.Const
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBApi {
    // TODO: Write the API requests that need to be made to the API
    // Example of a request: https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher -- THIS IS FOR SEARCH

    @GET("movie/top_rated?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getTopRatedMovies(): MoviesResponse

    // https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    @GET("movie/popular?api_key=${Const.TMDB_API_KEY}&language=en-US&")
    suspend fun getPopularMovies(): MoviesResponse

    // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
    @GET("movie/{movie_id}?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getMovieById(@Path("movie_id") id: Int): DetailedMovie
}