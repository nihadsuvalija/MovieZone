package com.example.moviezone.screen.sign_up

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.moviezone.model.CurrentUser
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel: ViewModel() {

    val TAG = "SignUpViewModel"

    fun registerUser(fullName: String, email: String, password: String) {
        CurrentUser.fullName = fullName
        CurrentUser.email = email
        CurrentUser.password = password

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            Log.i(TAG, "User successfully registered")
        }.addOnFailureListener {
            Log.i(TAG, it.message.toString())
        }
    }
}