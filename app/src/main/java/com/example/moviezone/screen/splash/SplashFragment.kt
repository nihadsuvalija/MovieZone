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
import com.google.firebase.auth.FirebaseAuth

class SplashFragment: Fragment() {

    private lateinit var binding: SplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.splash, container, false)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        viewModel.updateCurrentUser()
        // TO DO: Fix deprecated handler.
        Handler().postDelayed({
            if (CurrentUser.signedIn()) {
                viewModel.navigateToBase()
            } else {
                viewModel.navigateToWelcome()
            }
        }, 2000)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(binding.root))
    }
}