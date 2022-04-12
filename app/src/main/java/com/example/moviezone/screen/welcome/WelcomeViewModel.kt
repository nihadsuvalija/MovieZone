package com.example.moviezone.screen.welcome

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class WelcomeViewModel: ViewModel() {

    private var navController: NavController? = null
    private var viewInteractor: WelcomeViewInteractor? = null
    val TAG = "STATUS"

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInOptions: GoogleSignInOptions

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun setViewInteractor(viewInteractor: WelcomeViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun onSignUpClicked() {
        navController?.navigate(WelcomeFragmentDirections.navigateFromWelcomeToSignUp())
    }

    fun onSignInClicked() {
        navController?.navigate(WelcomeFragmentDirections.navigateFromWelcomeToSignIn())
    }

    fun setupGoogleSignIn(context: Context): Intent {
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)

        return googleSignInClient.signInIntent
    }

    fun handleSignInResult(result: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = result.getResult(ApiException::class.java)
            navController?.navigate(WelcomeFragmentDirections.navigateFromWelcomeToBase())
        } catch (e: ApiException) {
            viewInteractor?.displayPopUp(e.message.toString())
        }
    }
}