package com.example.moviezone.utils

class Const {
    companion object {
        const val USER_DB_KEY = "users"
        const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
        const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val TMDB_API_KEY = "dea192f1a4b8334963672850dcddf227"
        const val TMDB_API_READ_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZWExOTJmMWE0YjgzMzQ5NjM2NzI4NTBkY2RkZjIyNyIsInN1YiI6IjYyNGJkOTU1M2M0MzQ0MDA2NjE5Zjc3YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.sls09NbdiSQmfXeXSe1NvlQsckohJctfX5ZIBS5ereA"

        const val IMDB_API_KEY = "k_xq3oejz6"
        const val IMDB_BASE_URL = "https://imdb-api.com/en/API/"

        const val MOVIEGLU_CLIENT = "YOPA"
        const val MOVIEGLUE_API_ENDPOINT = "https://api-gate2.movieglu.com/"
        const val MOVIEGLU_TEST_API_KEY = "iN8Y2NeiMX4sUCFeUQDXb7o6k3dl6Zjt9sKfBCKn"
        const val MOVIEGLU_TEST_AUTHORIZATION = "Basic WU9QQV9YWDphY25NQ2dSaHVpQjk="
        const val MOVIEGLU_TEST_TERRITORY = "XX" // XX - sandbox (allows up to 10000 requests), US (allows for 75)
        const val MOVIEGLU_API_VERSION = "v200"
        const val MOVIEGLU_TEST_GEOLOCATION = "-22.0;14.0"
        const val MOVIEGLU_TEST_DEVICE_DATETIME = "2022-04-12T11:00:02.484Z"

        // ERRORS:
        const val EMAIL_FORMAT_ERROR = "Email format is not correct."
        const val PASSWORD_LENGTH_ERROR = "Password must be 6 or more characters."
        const val USER_DOESNT_EXIST_ERROR = "User doesn't exist."
        const val FULL_NAME_EMPTY_ERROR = "Full name field can't be empty."

        // LIMITS:
        const val MAX_TEXT_VIEW_LENGTH = 15
    }
}