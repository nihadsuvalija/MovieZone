package com.example.moviezone.repository

import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.model.MovieDetails
import com.example.moviezone.model.TrailerResponse
import com.example.moviezone.model.UpcomingResponse
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
}