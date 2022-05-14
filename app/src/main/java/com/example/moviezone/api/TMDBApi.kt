package com.example.moviezone.api

import com.example.moviezone.model.*
import com.example.moviezone.utils.Const
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/upcoming?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getUpcomingMovies() : UpcomingResponse

    @GET("movie/now_playing?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getNowPlayingMovies() : NowPlayingResponse

    @GET("movie/{movie_id}?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getMovieById(@Path("movie_id") movieId: Int): MovieDetails

    @GET("movie/{movie_id}/videos?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getMovieTrailerById(@Path("movie_id") movieId: Int): TrailerResponse

    @GET("movie/{movie_id}/credits?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getCreditsByMovieId(@Path("movie_id") movieId: Int): CreditsResponse

    @GET("movie/{movie_id}/reviews?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getReviewsById(@Path("movie_id") movieId: Int): ReviewResponse

    @GET("movie/{movie_id}/similar?api_key=${Const.TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int): SimilarResponse

    // Set include_adult to true and check the movies *devil*.
    @GET("search/movie?api_key=${Const.TMDB_API_KEY}&language=en-US&include_adult=true")
    suspend fun searchMoviesByTitle(@Query("query") title: String): SearchResponse

    // https://api.themoviedb.org/3/discover/movie?api_key=dea192f1a4b8334963672850dcddf227&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate
    @GET("discover/movie?api_key=${Const.TMDB_API_KEY}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate")
    suspend fun getDiscoverMovies(): DiscoverResponse

    // https://api.themoviedb.org/3/movie/top_rated?api_key=dea192f1a4b8334963672850dcddf227&language=en-US&page=1
    @GET("movie/top_rated?api_key=${Const.TMDB_API_KEY}&language=en-US")
    suspend fun getTopRatedMovies(): TopRatedResponse
}