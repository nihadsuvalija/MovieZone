package com.example.moviezone.repository

import com.example.moviezone.api.RetrofitInstance
import com.example.moviezone.api.response.NowShowingResponse
import com.example.moviezone.model.FilmDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FilmRepository {

    suspend fun getNowShowing(): Flow<NowShowingResponse> {
        return flow {
            val response = RetrofitInstance.api.getNowShowing()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFilmById(filmId: Int): Flow<FilmDetails> {
        return flow {
            val response = RetrofitInstance.api.getFilmById(filmId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}