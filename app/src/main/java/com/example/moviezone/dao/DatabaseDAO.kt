package com.example.moviezone.dao

import android.util.Log
import com.example.moviezone.model.User
import com.example.moviezone.utils.Const
import com.google.firebase.database.FirebaseDatabase

class DatabaseDAO {

    private var usersReference = FirebaseDatabase.getInstance(Const.FIREBASE_DATABASE_URL).getReference(Const.USERS_DB_KEY)

    fun getUserById(userId: String) {
        usersReference.child(userId).get().addOnSuccessListener {
            // TO DO: Do something with this user
            val user = it.getValue(User::class.java) as User
        }.addOnFailureListener {
            Log.i("ERROR", "getUserById: ${it.message.toString()}")
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
                    Log.i("ERROR", "addUser: ${it.message.toString()}")
                }
            }.addOnFailureListener {
                Log.i("ERROR", "addUser: ${it.message.toString()}")
            }
        } else {
            usersReference.child(user.id).setValue(user).addOnSuccessListener {
                // TO DO: Implement logic for on success of addition of user
            }.addOnFailureListener {
                // TO DO: Implement logic for on failure of addition of user.
                Log.i("ERROR", "addUser: ${it.message.toString()}")
            }
        }
    }

    fun updateUser(user: User) {
        addUser(user)
    }

    fun getLikedMovies(userId: Int) {

    }
}