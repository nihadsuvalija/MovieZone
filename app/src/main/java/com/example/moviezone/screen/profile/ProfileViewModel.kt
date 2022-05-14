package com.example.moviezone.screen.profile

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.dao.DatabaseDAO
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.screen.central.CentralFragmentDirections
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel: ViewModel(), ProfileViewModelInteractor {
    private var navController: NavController? = null
    private var viewInteractor: ProfileViewInteractor? = null
    private var mAuth = FirebaseAuth.getInstance()

    private var dao = DatabaseDAO();

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun setViewInteractor(viewInteractor: ProfileViewInteractor) {
        this.viewInteractor = viewInteractor
        dao.setProfileViewInteractor(viewInteractor)
    }

    fun signOut() {
        mAuth.signOut()
        CurrentUser.signedIn = false
        navController?.navigate(CentralFragmentDirections.navigateFromBaseToWelcome())
    }

    fun setProfileData() {
        setProfilePhoto()
        viewInteractor?.setEmail(CurrentUser.email)
        viewInteractor?.setFullName(CurrentUser.fullName)
    }

    fun privacyClick() {
        Log.i("TAG", navController.toString())
        navController?.navigate(CentralFragmentDirections.navigateFromBaseToPrivacyPolicy())
    }

    fun updateProfilePicture(imageUrl: String) {
        dao.updateUserProfileImage(imageUrl, mAuth.currentUser?.uid.toString())
        Log.i("TAG", "setProfilePhoto: $imageUrl")
        viewInteractor?.setProfilePhoto(imageUrl)
    }

    fun setProfilePhoto() {
        dao.setProfilePhoto(mAuth.currentUser?.uid.toString())
    }

    override fun changeProfileImage() {
        val galleryIntent = Intent()
        galleryIntent.type = "image/*";
        galleryIntent.action = Intent.ACTION_GET_CONTENT;

        viewInteractor?.changeProfilePhoto(galleryIntent)
    }
}