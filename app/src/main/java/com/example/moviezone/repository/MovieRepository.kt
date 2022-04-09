package com.example.moviezone.repository

import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.api.response.ReviewResponse
import com.example.moviezone.model.DetailedMovie
import com.example.moviezone.model.MovieCredits
import com.example.moviezone.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository {

    suspend fun getTopRatedMovies(): Flow<MoviesResponse> {
        return flow {
            val response = RetrofitInstance.api.getTopRatedMovies()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularMovies(): Flow<MoviesResponse> {
        return flow {
            val response = RetrofitInstance.api.getPopularMovies()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieById(movieId: Int): Flow<DetailedMovie> {
        return flow {
            val response = RetrofitInstance.api.getMovieById(movieId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCreditsByMovieId(movieId: Int): Flow<MovieCredits> {
        return flow {
            val response = RetrofitInstance.api.getCreditsByMovieId(movieId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    fun getReviewsByMovieId(movieId: Int): Flow<ReviewResponse> {
        return flow {
            val response = RetrofitInstance.api.getReviewsByMovieId(movieId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}