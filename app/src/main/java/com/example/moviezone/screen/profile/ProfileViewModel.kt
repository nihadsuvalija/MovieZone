package com.example.moviezone.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.R
import com.example.moviezone.screen.base.BaseFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel: ViewModel() {
    private var navController: NavController? = null
    private var viewInteractor: ProfileViewInteractor? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun setViewInteractor(viewInteractor: ProfileViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        navController?.navigate(BaseFragmentDirections.navigateFromBaseToWelcome())
    }

    fun setProfileData() {
        viewInteractor?.setProfilePicture(FirebaseAuth.getInstance().currentUser?.photoUrl.toString())
        viewInteractor?.setEmail(FirebaseAuth.getInstance().currentUser?.email.toString())
        viewInteractor?.setFullName(FirebaseAuth.getInstance().currentUser?.displayName.toString())
    }

    fun privacyClick() {
        Log.i("TAG", navController.toString())
        navController?.navigate(BaseFragmentDirections.navigateFromBaseToPrivacyPolicy())
    }
}