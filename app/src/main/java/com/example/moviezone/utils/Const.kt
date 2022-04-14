package com.example.moviezone.utils

class Const {
    companion object {
        const val USER_DB_KEY = "users"
        const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
        const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val TMDB_API_KEY = "dea192f1a4b8334963672850dcddf227"
        const val TMDB_API_READ_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZWExOTJmMWE0YjgzMzQ5NjM2NzI4NTBkY2RkZjIyNyIsInN1YiI6IjYyNGJkOTU1M2M0MzQ0MDA2NjE5Zjc3YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.sls09NbdiSQmfXeXSe1NvlQsckohJctfX5ZIBS5ereA"

        const val BASE_YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
        const val GOOGLE_API_KEY = "AIzaSyAW4zidNs2meUaTJpnfAodU_IhpO-BSOkA"

        // ERRORS:
        const val EMAIL_FORMAT_ERROR = "Email format is not correct."
        const val PASSWORD_LENGTH_ERROR = "Password must be 6 or more characters."
        const val USER_DOESNT_EXIST_ERROR = "User doesn't exist."
        const val FULL_NAME_EMPTY_ERROR = "Full name field can't be empty."

        // LIMITS:
        const val MAX_TEXT_VIEW_LENGTH = 15
    }
}