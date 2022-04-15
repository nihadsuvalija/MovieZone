package com.example.moviezone.api

import com.example.moviezone.model.*
import com.example.moviezone.utils.Const
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBApi {
    @GET("movie/upcoming?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getUpcomingMovies() : UpcomingResponse

    @GET("movie/now_playing?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getNowPlayingMovies() : NowPlayingResponse

    @GET("movie/{movie_id}?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getMovieById(@Path("movie_id") movieId: Int): MovieDetails

    @GET("movie/{movie_id}/videos?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getMovieTrailerById(@Path("movie_id") movieId: Int): TrailerResponse

    @GET("movie/{movie_id}}/credits?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getCreditsByMovieId(@Path("movie_id") movieId: Int): CreditsResponse

    @GET("movie/{movie_id}/reviews?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getReviewsById(@Path("movie_id") movieId: Int): ReviewResponse
}