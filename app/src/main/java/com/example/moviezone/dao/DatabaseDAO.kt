package com.example.moviezone.dao

import android.util.Log
import com.example.moviezone.model.CurrentUser
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
            val favorites = mutableListOf("")
            for (snap in it.children) {
                favorites.add(snap.value.toString())
            }
            CurrentUser.favorites = favorites
        }.addOnFailureListener {
            // TO DO: Implement logic for on failure of getting favorite movies of user.
            Log.i(TAG, "getFavorites: ${it.message.toString()}")
        }
    }

    // MOVIES:

    fun addFavorite(movieId: String, userId: String) {
        usersReference.child(userId).child(Const.FAVORITES_KEY).child(movieId).setValue(movieId).addOnSuccessListener {
        // TO DO: Implement logic for on success of adding a favorite movie.
        }.addOnFailureListener {
            // TO DO: Implement logic for on failure of adding a favorite movie.
            Log.i(TAG, "addFavorite: ${it.message.toString()}")
        }
    }
}