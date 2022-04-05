package com.example.moviezone.screen.sign_in

import android.content.pm.SigningInfo
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.moviezone.utils.Const
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel: ViewModel() {

    val TAG = "SignInViewModel"
    private var viewInteractor: SignInViewInteractor? = null

    fun signInWithEmailAndPassword(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // TO DO: Navigate to the next screen
                    Log.i(TAG, "User successfully logged in.")
                }.addOnFailureListener {
                    viewInteractor?.setEmailError(Const.USER_DOESNT_EXIST_ERROR)
            }
        } else {
            if (!isEmailValid(email)) viewInteractor?.setEmailError(Const.EMAIL_FORMAT_ERROR)
            if (!isPasswordValid(password)) viewInteractor?.setPasswordError(Const.PASSWORD_LENGTH_ERROR)
        }
    }

    fun setViewInteractor(viewInteractor: SignInViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    /* HELPER METHODS: */

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }
}