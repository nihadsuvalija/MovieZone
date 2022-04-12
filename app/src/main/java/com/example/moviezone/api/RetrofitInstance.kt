package com.example.moviezone.api

import com.example.moviezone.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: TMDBApi by lazy {
        Retrofit.Builder()
            .baseUrl(Const.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBApi::class.java)
    }

    val imdbApi: IMDBApi by lazy {
        Retrofit.Builder()
            .baseUrl(Const.IMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDBApi::class.java)
    }
}