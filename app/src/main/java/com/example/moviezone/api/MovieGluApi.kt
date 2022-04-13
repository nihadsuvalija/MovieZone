package com.example.moviezone.api

import com.example.moviezone.api.response.NowShowingResponse
import com.example.moviezone.model.FilmDetails
import com.example.moviezone.utils.Const
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieGluApi {

    @Headers(
        "client: ${Const.MOVIEGLU_CLIENT}",
        "x-api-key: ${Const.MOVIEGLU_TEST_API_KEY}",
        "authorization: ${Const.MOVIEGLU_TEST_AUTHORIZATION}",
        "territory: ${Const.MOVIEGLU_TEST_TERRITORY}",
        "api-version: ${Const.MOVIEGLU_API_VERSION}",
        "geolocation: ${Const.MOVIEGLU_TEST_GEOLOCATION}", // Replace with actual geolocation for real world usage
        "device-datetime: ${Const.MOVIEGLU_TEST_DEVICE_DATETIME}"
    )
    @GET("filmsNowShowing")
    suspend fun getNowShowing() : NowShowingResponse

    @Headers(
        "client: ${Const.MOVIEGLU_CLIENT}",
        "x-api-key: ${Const.MOVIEGLU_TEST_API_KEY}",
        "authorization: ${Const.MOVIEGLU_TEST_AUTHORIZATION}",
        "territory: ${Const.MOVIEGLU_TEST_TERRITORY}",
        "api-version: ${Const.MOVIEGLU_API_VERSION}",
        "geolocation: ${Const.MOVIEGLU_TEST_GEOLOCATION}", // Replace with actual geolocation for real world usage
        "device-datetime: ${Const.MOVIEGLU_TEST_DEVICE_DATETIME}"
    )
    @GET("filmDetails")
    suspend fun getFilmById(@Query("film_id") filmId: Int) : FilmDetails

}