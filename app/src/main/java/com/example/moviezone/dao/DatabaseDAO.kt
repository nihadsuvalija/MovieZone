package com.example.moviezone.dao

import android.util.Log
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.model.Movie
import com.example.moviezone.model.MovieDetails
import com.example.moviezone.model.User
import com.example.moviezone.utils.Const
import com.google.firebase.database.FirebaseDatabase

class DatabaseDAO {

    private var usersReference = FirebaseDatabase.getInstance(Const.FIREBASE_DATABASE_URL).getReference(Const.USERS_DB_KEY)

    private val TAG = "ERROR"

    fun getUserById(userId: String) {
        usersReference.child(userId).get().addOnSuccessListener {
            // TO DO: Do something with this user
            val user = it.getValue(User::class.java) as User
        }.addOnFailureListener {
            Log.i(TAG, "getUserById: ${it.message.toString()}")
        }
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

    fun getFavorites(userId: String) {
        usersReference.child(userId).child(Const.FAVORITES_KEY).get().addOnSuccessListener {
            // TO DO: Implement logic for on success of getting favorite movies of user.
            val favorites: MutableList<String> = mutableListOf()
            for (snap in it.children) {
                favorites.add(snap.key.toString())
            }
            CurrentUser.favorites = favorites
        }.addOnFailureListener {
            // TO DO: Implement logic for on failure of getting favorite movies of user.
            Log.e(TAG, it.message.toString())
        }
    }

    // MOVIES:

    fun addToFavorites(movie: MovieDetails, userId: String) {
        usersReference.child(userId).child(Const.FAVORITES_KEY).child(movie.id.toString()).setValue(movie)
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