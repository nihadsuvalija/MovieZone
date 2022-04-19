package com.example.moviezone.screen.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.SplashBinding
import com.example.moviezone.model.CurrentUser

class SplashFragment: Fragment() {

    private var binding: SplashBinding? = null
    private var viewModel: SplashViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.splash, container, false)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]



        // TO DO: Fix deprecated handler.
        Handler().postDelayed({
            if (viewModel?.isLogged() == true) {
                viewModel?.navigateToBase()
            } else {
                viewModel?.navigateToWelcome()
            }
        }, 2000)

        viewModel?.updateCurrentUser()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.root?.let { Navigation.findNavController(it) }
            ?.let { viewModel?.setNavController(it) }
    }
}