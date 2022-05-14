package com.example.moviezone.screen.privacypolicy

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.utils.Const

class PrivacyPolicyViewModel: ViewModel() {
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onBackPressed() {
        navController?.navigate(PrivacyPolicyFragmentDirections.navigateFromPrivacyPolicyToBase(
            Const.PROFILE_PAGE_INDEX))
    }
}