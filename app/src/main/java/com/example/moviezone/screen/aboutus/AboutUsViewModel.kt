package com.example.moviezone.screen.aboutus

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.utils.Const

class AboutUsViewModel: ViewModel() {
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onBackPressed() {
        navController?.navigate(
            AboutUsFragmentDirections.navigateFromAboutUsToBase(
                Const.PROFILE_PAGE_INDEX
            )
        )
    }
}