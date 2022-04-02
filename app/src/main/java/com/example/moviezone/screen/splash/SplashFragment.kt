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

        Handler().postDelayed(Runnable {
            Navigation.findNavController(binding.root).navigate(R.id.navigateFromSplashToWelcome)
        }, 2000)

        return binding.root
    }
}