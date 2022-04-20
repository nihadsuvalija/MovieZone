package com.example.moviezone.repository

import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository {
    suspend fun getUpcomingMovies(): Flow<UpcomingResponse> {
        return flow {
            val response = RetrofitInstance.tmdbApi.getUpcomingMovies()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieById(movieId: Int): Flow<MovieDetails> {
        return flow {
            val response = RetrofitInstance.tmdbApi.getMovieById(movieId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieTrailerById(movieId: Int): Flow<TrailerResponse> {
        return flow {
            val response = RetrofitInstance.tmdbApi.getMovieTrailerById(movieId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCreditsByMovieId(movieId: Int): Flow<CreditsResponse> {
        return flow {
            val response = RetrofitInstance.tmdbApi.getCreditsByMovieId(movieId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getReviewsById(movieId: Int): Flow<ReviewResponse> {
        return flow {
            val response = RetrofitInstance.tmdbApi.getReviewsById(movieId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNowPlayingMovies(): Flow<NowPlayingResponse> {
        return flow {
            val response = RetrofitInstance.tmdbApi.getNowPlayingMovies()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchMoviesByTitle(title: String): Flow<SearchResponse> {
        return flow {
            val response = RetrofitInstance.tmdbApi.searchMoviesByTitle(title)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDiscoverMovies(): Flow<DiscoverResponse> {
        return flow {
            val response = RetrofitInstance.tmdbApi.getDiscoverMovies()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}