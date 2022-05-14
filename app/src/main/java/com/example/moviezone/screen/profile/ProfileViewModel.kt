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

    // MORE SETTINGS:
    fun privacyClick() {
        navController?.navigate(CentralFragmentDirections.navigateFromBaseToPrivacyPolicy())
    }

    fun helpAndFeedbackClick() {
        navController?.navigate(CentralFragmentDirections.navigateFromBaseToHelpAndFeedback())
    }

    fun aboutUsClick() {
        navController?.navigate(CentralFragmentDirections.navigatefromBaseToAboutUs())
    }

    // ACCOUNT SETTINGS:
    override fun changePasswordClick() {
        mAuth.sendPasswordResetEmail(mAuth.currentUser?.email.toString())
        viewInteractor?.displayMessage("Password reset email sent.")
    }

    override fun changeProfilePhotoClick() {
        val galleryIntent = Intent()
        galleryIntent.type = "image/*";
        galleryIntent.action = Intent.ACTION_GET_CONTENT;

        viewInteractor?.changeProfilePhoto(galleryIntent)
    }

    fun updateProfilePhoto(photoPath: String) {
        dao.updateUserProfileImage(photoPath, mAuth.currentUser?.uid.toString())
        viewInteractor?.setProfilePhoto(photoPath)
    }

    fun setProfilePhoto() {
        dao.setProfilePhoto(mAuth.currentUser?.uid.toString())
    }

}