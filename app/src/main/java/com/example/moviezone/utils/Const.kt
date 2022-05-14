package com.example.moviezone.utils

class Const {
    companion object {
        // DATABASE:
        const val FIREBASE_DATABASE_URL = "https://moviezone-3a43b-default-rtdb.europe-west1.firebasedatabase.app/"
        const val USERS_DB_KEY = "Users"
        const val FAVORITES_KEY = "favorites"

        // TMDB:
        const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
        const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val TMDB_API_KEY = "dea192f1a4b8334963672850dcddf227"

        // YOUTUBE:
        const val YOUTUBE_API_KEY = "AIzaSyAW4zidNs2meUaTJpnfAodU_IhpO-BSOkA"

        // ERRORS:
        const val EMAIL_FORMAT_ERROR = "Email format is not correct."
        const val PASSWORD_LENGTH_ERROR = "Password must be 6 or more characters."
        const val USER_DOESNT_EXIST_ERROR = "User doesn't exist."
        const val FULL_NAME_EMPTY_ERROR = "Full name field can't be empty."

        // PAGE INDEXES:
        const val HOME_PAGE_INDEX = 0
        const val SEARCH_PAGE_INDEX = 1
        const val PROFILE_PAGE_INDEX = 2

        // COLORS:
        const val THEME_BLUE_COLOR = "#12CDD9"
        const val WHITE_COLOR = "#FFFFFF"

        // SETTINGS OPTIONS
        const val CHANGE_PROFILE_PHOTO = "Change Profile Picture"
        const val CHANGE_PASSWORD = "Change Password"
    }
}