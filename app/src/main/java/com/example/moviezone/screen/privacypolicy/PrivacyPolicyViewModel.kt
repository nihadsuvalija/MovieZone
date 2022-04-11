package com.example.moviezone.screen.privacypolicy

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class PrivacyPolicyViewModel: ViewModel() {
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onBackPressed() {
        navController?.navigate(PrivacyPolicyFragmentDirections.navigateFromPrivacyPolicyToBase())
    }
}