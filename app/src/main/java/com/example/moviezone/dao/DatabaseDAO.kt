package com.example.moviezone.dao

import android.net.Uri
import android.util.Log
import com.example.moviezone.model.*
import com.example.moviezone.screen.home.HomeViewInteractor
import com.example.moviezone.screen.profile.ProfileViewInteractor
import com.example.moviezone.utils.Const
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

class DatabaseDAO {

    private var usersReference = FirebaseDatabase.getInstance(Const.FIREBASE_DATABASE_URL).getReference(Const.USERS_DB_KEY)
    private var homeViewInteractor: HomeViewInteractor? = null
    private var profileViewInteractor: ProfileViewInteractor? = null

    private val TAG = "ERROR"

    fun setHomeViewInteractor(homeViewInteractor: HomeViewInteractor) {
        this.homeViewInteractor = homeViewInteractor
    }

    fun setProfileViewInteractor(profileViewInteractor: ProfileViewInteractor) {
        this.profileViewInteractor = profileViewInteractor
    }

    fun getUserById(userId: String): Task<DataSnapshot> {
        return usersReference.child(userId).get()
    }

    fun addUser(user: User) {
        if (user.id.isNullOrEmpty()) {
            usersReference.get().addOnSuccessListener {
                val userId = it.children.count().toString()
                usersReference.child(userId).setValue(user).addOnSuccessListener {
                    // TO DO: Implement logic for on success of addition of user
                }.addOnFailureListener {
                    // TO DO: Implement logic for on failure of addition of user.
                    Log.i(TAG, "addUser: ${it.message.toString()}")
                }
            }.addOnFailureListener {
                Log.i(TAG, "addUser: ${it.message.toString()}")
            }
        } else {
            usersReference.child(user.id).setValue(user).addOnSuccessListener {
                // TO DO: Implement logic for on success of addition of user
            }.addOnFailureListener {
                // TO DO: Implement logic for on failure of addition of user.
                Log.i(TAG, "addUser: ${it.message.toString()}")
            }
        }
    }

    fun updateUser(user: User) {
        addUser(user)
    }

    fun setProfilePhoto(userId: String) {
        getUserById(userId).addOnSuccessListener {
            val user = it.getValue(User::class.java) as User
            homeViewInteractor?.setProfilePhoto(user.photoPath.toString())
            profileViewInteractor?.setProfilePhoto(user.photoPath.toString())
        }
    }

    fun updateUserProfileImage(photoPath: String, userId: String) {
        getUserById(userId).addOnSuccessListener {
            val user = it.getValue(User::class.java) as User
            user.photoPath = photoPath
            updateUser(user)
        }
    }

    fun getFavorites(userId: String) {
        usersReference.child(userId).child(Const.FAVORITES_KEY).get().addOnSuccessListener {
            // TO DO: Implement logic for on success of getting favorite movies of user.
            val favorites: MutableList<FavoriteMovie> = mutableListOf()
            for (snap in it.children) {
                val movie = snap.getValue(FavoriteMovie::class.java) as FavoriteMovie
                println(movie.id)
                favorites.add(movie)
            }
            CurrentUser.favorites = favorites

            homeViewInteractor?.setFavoriteMovies(favorites)
        }.addOnFailureListener {
            // TO DO: Implement logic for on failure of getting favorite movies of user.
            Log.e(TAG, it.message.toString())
        }
    }

    // MOVIES:

    fun addToFavorites(movie: MovieDetails, userId: String) {
        val favoriteMovie = FavoriteMovie(movie.id, movie.posterPath, movie.voteAverage)
        usersReference.child(userId).child(Const.FAVORITES_KEY).child(favoriteMovie.id.toString()).setValue(favoriteMovie)
            .addOnSuccessListener {
                // TO DO: Implement logic for on success of adding a favorite movie.
            }.addOnFailureListener {
                // TO DO: Implement logic for on failure of adding a favorite movie.
                Log.e(TAG, it.message.toString())
            }
    }

    fun removeFromFavorites(movieId: String, userId: String) {
        usersReference.child(userId).child(Const.FAVORITES_KEY).child(movieId).removeValue()
            .addOnSuccessListener {
                // TO DO: Implement logic for on success of removing a favorite movie.
            }
            .addOnFailureListener {
                Log.e(TAG, it.message.toString())
            }
    }
}