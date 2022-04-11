package com.example.moviezone.api

import com.example.moviezone.api.response.ReviewResponse
import com.example.moviezone.model.DetailedMovie
import com.example.moviezone.model.MovieCredits
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

    // https://api.themoviedb.org/3/movie/123/credits?api_key=dea192f1a4b8334963672850dcddf227&language=en-US
    @GET("movie/{movie_id}}/credits?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getCreditsByMovieId(@Path("movie_id") id: Int): MovieCredits

    // https://api.themoviedb.org/3/movie/150/reviews?api_key=dea192f1a4b8334963672850dcddf227&language=en-US&page=1
    @GET("movie/{movie_id}}/reviews?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getReviewsByMovieId(@Path("movie_id") id: Int): ReviewResponse
}