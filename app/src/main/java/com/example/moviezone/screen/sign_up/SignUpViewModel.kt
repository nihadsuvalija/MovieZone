package com.example.moviezone.screen.sign_up

import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.utils.Const
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class SignUpViewModel: ViewModel() {

    val TAG = "SignUpViewModel"
    private var viewInteractor: SignUpViewInteractor? = null
    private var navController: NavController? = null

    fun registerUser(fullName: String, email: String, password: String) {
        if (isFullNameValid(fullName) && isEmailValid(email) && isPasswordValid(password)) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    FirebaseAuth.getInstance().currentUser?.updateProfile(
                        userProfileChangeRequest {
                            displayName = fullName
                        }
                    )
                    viewInteractor?.clearInputFields()
                    navController?.navigate(SignUpFragmentDirections.navigateFromSignUpToSignIn())
                }.addOnFailureListener {
                    viewInteractor?.setFullNameError(it.message.toString())
                    viewInteractor?.setEmailError(it.message.toString())
                    viewInteractor?.setPasswordError(it.message.toString())
                }
        } else {
            if (!isFullNameValid(fullName)) viewInteractor?.setFullNameError(Const.FULL_NAME_EMPTY_ERROR)
            if (!isEmailValid(email)) viewInteractor?.setEmailError(Const.EMAIL_FORMAT_ERROR)
            if (!isPasswordValid(password)) viewInteractor?.setPasswordError(Const.PASSWORD_LENGTH_ERROR)
        }
    }

    fun onBackPressed() {
        navController?.navigate(SignUpFragmentDirections.navigateFromSignUpToWelcome())
    }

    /* SETTER METHODS: */
    fun setViewInteractor(viewInteractor: SignUpViewInteractor) {
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

    private fun isFullNameValid(fullName: String): Boolean {
        return fullName.isNotEmpty()
    }
}