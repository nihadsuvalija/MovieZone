package com.example.moviezone.api

import com.example.moviezone.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: MovieGluApi by lazy {
        Retrofit.Builder()
            .baseUrl(Const.MOVIEGLUE_API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieGluApi::class.java)
    }

}