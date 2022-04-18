package com.example.moviezone.screen.welcome

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.model.CurrentUser
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
            CurrentUser.update(
                account.id.toString(),
                account.displayName.toString(),
                account.email.toString(),
                account.photoUrl.toString()
            )
            navController?.navigate(WelcomeFragmentDirections.navigateFromWelcomeToBase())
        } catch (e: ApiException) {
            viewInteractor?.displayPopUp(e.message.toString())
        }
    }
}