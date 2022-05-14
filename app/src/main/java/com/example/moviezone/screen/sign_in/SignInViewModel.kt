package com.example.moviezone.screen.sign_in

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.utils.Const
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class SignInViewModel: ViewModel() {

    private var viewInteractor: SignInViewInteractor? = null
    private var navController: NavController? = null
    private lateinit var googleSignInClient: GoogleSignInClient
    private var mAuth = FirebaseAuth.getInstance()

    fun signInWithEmailAndPassword(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // TO DO: Navigate to the next screen
                    viewInteractor?.clearInputFields()
                    CurrentUser.update(
                        mAuth.currentUser?.uid.toString(),
                        mAuth.currentUser?.displayName.toString(),
                        email,
                        mAuth.currentUser?.photoUrl.toString()
                    )
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