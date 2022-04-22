package com.example.moviezone.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.screen.central.CentralFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel: ViewModel() {
    private var navController: NavController? = null
    private var viewInteractor: ProfileViewInteractor? = null
    private var mAuth = FirebaseAuth.getInstance()

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun setViewInteractor(viewInteractor: ProfileViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun signOut() {
        mAuth.signOut()
        CurrentUser.signedIn = false
        navController?.navigate(CentralFragmentDirections.navigateFromBaseToWelcome())
    }

    fun setProfileData() {
        viewInteractor?.setProfilePicture(mAuth.currentUser?.photoUrl.toString())
        viewInteractor?.setEmail(CurrentUser.email)
        viewInteractor?.setFullName(CurrentUser.fullName)
    }

    fun privacyClick() {
        Log.i("TAG", navController.toString())
        navController?.navigate(CentralFragmentDirections.navigateFromBaseToPrivacyPolicy())
    }
}