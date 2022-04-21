package com.example.moviezone.screen.sign_up

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.dao.DatabaseDAO
import com.example.moviezone.model.User
import com.example.moviezone.utils.Const
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class SignUpViewModel: ViewModel() {

    private var viewInteractor: SignUpViewInteractor? = null
    private var navController: NavController? = null
    private var mAuth = FirebaseAuth.getInstance()
    private var dbDao = DatabaseDAO()

    fun registerUser(fullName: String, email: String, password: String) {
        if (isFullNameValid(fullName) && isEmailValid(email) && isPasswordValid(password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    mAuth.currentUser?.updateProfile(
                        userProfileChangeRequest {
                            displayName = fullName
                        }
                    )

                    viewInteractor?.clearInputFields()

                    val user = User (
                        id = mAuth.currentUser?.uid.toString(),
                        email = email,
                        password = password,
                        fullName = fullName
                            )
                    dbDao.addUser(user)

                    // For some reason it treats the user as if he's logged in, so we log him out.
                    mAuth.signOut()

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
        dbDao.setViewInteractor(viewInteractor)
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