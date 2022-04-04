package com.example.moviezone.screen.sign_in

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel: ViewModel() {

    val TAG = "SignInViewModel"

    fun signInWithEmailAndPassword(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnSuccessListener {
            Log.i(TAG, "User successfully logged in.")
        }.addOnFailureListener {
            Log.i(TAG, it.message.toString())
        }
    }
}