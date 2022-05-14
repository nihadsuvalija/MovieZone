package com.example.moviezone.screen.helpandfeedback

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviezone.utils.Const

class HelpAndFeedbackViewModel: ViewModel() {

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onBackPressed() {
        navController?.navigate(
            HelpAndFeedbackFragmentDirections.navigateFromHelpAndFeedbackToBase(
                Const.PROFILE_PAGE_INDEX
            )
        )
    }
}