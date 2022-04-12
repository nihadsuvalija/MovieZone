package com.example.moviezone.api

import com.example.moviezone.imdbmodel.MoviesResponse
import com.example.moviezone.utils.Const
import retrofit2.http.GET

interface IMDBApi {

    @GET("InTheaters/${Const.IMDB_API_KEY}")
    suspend fun getInTheaters(): MoviesResponse
}