package com.example.moviezone.repository

import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.imdbmodel.Movie
import com.example.moviezone.imdbmodel.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class IMDBMovieRepository {

    suspend fun getInTheaters(): Flow<MoviesResponse> {
        return flow {
            val response = RetrofitInstance.imdbApi.getInTheaters()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}