package com.example.moviezone.dao

import android.util.Log
import android.widget.Toast
import com.example.moviezone.model.User
import com.example.moviezone.repository.UsersRepository
import com.example.moviezone.screen.ViewInteractor
import com.example.moviezone.utils.Const
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

class DatabaseDAO {

    private var usersReference = FirebaseDatabase.getInstance("https://moviezone-3a43b-default-rtdb.europe-west1.firebasedatabase.app/").getReference(Const.USERS_DB_KEY)
    private var viewInteractor: ViewInteractor? = null
    private var usersRepository: UsersRepository? = null

    fun setViewInteractor(viewInteractor: ViewInteractor?) {
        this.viewInteractor = viewInteractor
    }

    fun setUserRepository(usersRepository: UsersRepository?) {
        this.usersRepository = usersRepository
    }

    fun getUsers() {
        usersReference.get().addOnSuccessListener {
            // TO DO: Do something when all users are fetched.
            val users = mutableListOf<User>()
            for (snap in it.children) {
                val user = it.getValue(User::class.java) as User
                users.add(user)
            }
            usersRepository?.users = users
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
        if (user.id.isNullOrEmpty()) {
            usersReference.get().addOnSuccessListener {
                val userId = it.children.count().toString()
                usersReference.child(userId).setValue(user).addOnSuccessListener {
                    // TO DO: Implement logic for on success of addition of user
                }.addOnFailureListener {
                    // TO DO: Implement logic for on failure of addition of user.
                    viewInteractor?.displayMessage(it.message.toString())
                }
            }.addOnFailureListener {
                viewInteractor?.displayMessage(it.message.toString())
            }
        } else {
            usersReference.child(user.id).setValue(user).addOnSuccessListener {
                // TO DO: Implement logic for on success of addition of user
            }.addOnFailureListener {
                // TO DO: Implement logic for on failure of addition of user.
                viewInteractor?.displayMessage(it.message.toString())
            }
        }
    }

    fun updateUser(user: User) {
        addUser(user)
    }

    fun getLikedMovies(userId: Int) {

    }
}