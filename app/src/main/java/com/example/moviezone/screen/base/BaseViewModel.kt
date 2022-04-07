package com.example.moviezone.screen.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class BaseViewModel: ViewModel() {

    private var viewInteractor: BaseViewInteractor? = null
    private var navController: NavController? = null

    fun setViewInteractor(viewInteractor: BaseViewInteractor) {
        this.viewInteractor = viewInteractor
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }
}