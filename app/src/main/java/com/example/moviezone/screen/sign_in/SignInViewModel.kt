package com.example.moviezone.screen.sign_in

import android.content.pm.SigningInfo
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.R
import com.example.moviezone.utils.Const
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel: ViewModel() {

    val TAG = "SignInViewModel"
    private var viewInteractor: SignInViewInteractor? = null
    private var navController: NavController? = null

    fun signInWithEmailAndPassword(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // TO DO: Navigate to the next screen
                    viewInteractor?.clearInputFields()
                }.addOnFailureListener {
                    viewInteractor?.setEmailError(Const.USER_DOESNT_EXIST_ERROR)
            }
        } else {
            if (!isEmailValid(email)) viewInteractor?.setEmailError(Const.EMAIL_FORMAT_ERROR)
            if (!isPasswordValid(password)) viewInteractor?.setPasswordError(Const.PASSWORD_LENGTH_ERROR)
        }
    }

    fun onBackPressed() {
        navController?.navigate(R.id.navigateFromSignInToWelcome)
    }

    /* SETTER METHODS: */
    fun setViewInteractor(viewInteractor: SignInViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    /* HELPER METHODS: */

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }
}