package com.example.moviezone.screen.sign_in

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Patterns
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.R
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.utils.Const
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase

class SignInViewModel: ViewModel() {

    val TAG = "SignInViewModel"
    private var viewInteractor: SignInViewInteractor? = null
    private var navController: NavController? = null
    private lateinit var googleSignInClient: GoogleSignInClient

    fun signInWithEmailAndPassword(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // TO DO: Navigate to the next screen
                    viewInteractor?.clearInputFields()
                    CurrentUser.update(FirebaseAuth.getInstance().currentUser?.displayName.toString(), email)
                    navController?.navigate(SignInFragmentDirections.navigateFromSignInToBase())
                }.addOnFailureListener {
                    viewInteractor?.setEmailError(Const.USER_DOESNT_EXIST_ERROR)
            }
        } else {
            if (!isEmailValid(email)) viewInteractor?.setEmailError(Const.EMAIL_FORMAT_ERROR)
            if (!isPasswordValid(password)) viewInteractor?.setPasswordError(Const.PASSWORD_LENGTH_ERROR)
        }
    }

    fun onBackPressed() {
        navController?.navigate(SignInFragmentDirections.navigateFromSignInToWelcome())
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