package com.example.moviezone.screen.sign_in

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.SignInBinding

/*
* REMINDER: FIX THE SPACE AFTER AUTOFILLING THE INFORMATION
*/

class SignInFragment: Fragment(), SignInViewInteractor{

    val TAG = "SignInFragment"

    private lateinit var binding: SignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.sign_in, container, false)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        viewModel.setViewInteractor(this)
        viewModel.setNavController(Navigation.findNavController(binding.root))

        binding.ivBackButton.setOnClickListener {
            viewModel.onBackPressed()
        }

        binding.btnSignInSignin.setOnClickListener {
            viewModel.signInWithEmailAndPassword(binding.etEmailSignin.text.toString(),
                                                 binding.etPasswordSignin.text.toString())

        }

        return binding.root
    }

    /* SignInViewInteractor METHODS */

    override fun setEmailError(error: String) {
        binding.etEmailSignin.error = error
    }

    override fun setPasswordError(error: String) {
        binding.etPasswordSignin.error = error
    }

    override fun clearInputFields() {
        binding.etEmailSignin.text.clear()
        binding.etPasswordSignin.text.clear()
    }
}