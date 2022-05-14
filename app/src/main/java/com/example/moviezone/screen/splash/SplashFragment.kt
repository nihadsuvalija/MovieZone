package com.example.moviezone.screen.splash

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.SplashBinding
import com.example.moviezone.model.CurrentUser

class SplashFragment: Fragment(), SplashViewInteractor{

    private var binding: SplashBinding? = null
    private var viewModel: SplashViewModel? = null
    private var connManager: ConnectivityManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.splash, container, false)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        viewModel?.setViewInteractor(this)

        connManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // While there's no connection, keep displaying the error.
            if (connManager?.activeNetwork == null) {
                viewModel?.displayNoConnectionError()
                Handler(Looper.getMainLooper()).postDelayed({
                    this.onCreateView(inflater, container, savedInstanceState)
                }, 10000)
            } else {
                // Once connection has been established, move on.
                Handler(Looper.getMainLooper()).postDelayed({
                    if (viewModel?.isLogged() == true) {
                        viewModel?.navigateToBase()
                    } else {
                        viewModel?.navigateToWelcome()
                    }
                }, 2000)
                viewModel?.updateCurrentUser()
            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.root?.let { Navigation.findNavController(it) }
            ?.let { viewModel?.setNavController(it) }
    }

    override fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}