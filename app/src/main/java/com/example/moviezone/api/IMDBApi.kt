package com.example.moviezone.api

import com.example.moviezone.imdbmodel.MovieDetails
import com.example.moviezone.imdbmodel.MoviesResponse
import com.example.moviezone.utils.Const
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDBApi {

    @GET("InTheaters/${Const.IMDB_API_KEY}")
    suspend fun getInTheaters(): MoviesResponse

    @GET("Title/${Const.IMDB_API_KEY}/{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId: String): MovieDetails
}