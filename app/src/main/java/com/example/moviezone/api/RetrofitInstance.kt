package com.example.moviezone.api

import com.example.moviezone.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val tmdbApi: TMDBApi by lazy {
        Retrofit.Builder()
            .baseUrl(Const.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBApi::class.java)
    }

}