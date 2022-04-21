package com.example.moviezone.dao

import android.widget.Toast
import com.example.moviezone.model.User
import com.example.moviezone.screen.ViewInteractor
import com.example.moviezone.utils.Const
import com.google.firebase.database.FirebaseDatabase

class DatabaseDAO {

    private var usersReference = FirebaseDatabase.getInstance().getReference(Const.USERS_DB_KEY)
    private var viewInteractor: ViewInteractor? = null

    fun setViewInteractor(viewInteractor: ViewInteractor?) {
        this.viewInteractor = viewInteractor
    }

    fun getUsers() {
        usersReference.get().addOnSuccessListener {
            // TO DO: Do something when all users are fetched.
        }.addOnFailureListener {
            viewInteractor?.displayMessage(it.message.toString())
        }
    }

    fun getUserById(userId: String) {
        usersReference.child(userId).get().addOnSuccessListener {
            // TO DO: Do something when the user is fetched.
        }.addOnFailureListener {
            viewInteractor?.displayMessage(it.message.toString())
        }
    }

    fun addUser(user: User) {
        usersReference.child(user.id).setValue(user).addOnSuccessListener {
            // TO DO: Implement logic for on success of addition of user
        }.addOnFailureListener {
            // TO DO: Implement logic for on failure of addition of user.
            viewInteractor?.displayMessage(it.message.toString())
        }
    }

    fun updateUser(user: User) {
        addUser(user)
    }

    fun getLikedMovies(userId: Int) {

    }
}