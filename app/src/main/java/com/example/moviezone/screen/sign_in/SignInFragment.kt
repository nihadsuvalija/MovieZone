package com.example.moviezone.screen.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private var binding: SignInBinding? = null
    private var viewModel: SignInViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.sign_in, container, false)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        viewModel?.setViewInteractor(this)

        binding?.ivBackButton?.setOnClickListener {
            viewModel?.onBackPressed()
        }

        binding?.btnSignInSignin?.setOnClickListener {
            viewModel?.signInWithEmailAndPassword(
                binding?.etEmailSignin?.text.toString(),
                binding?.etPasswordSignin?.text.toString()
            )
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.root?.let { Navigation.findNavController(it) }
            ?.let { viewModel?.setNavController(it) }
    }

    /* SignInViewInteractor METHODS */
    override fun setEmailError(error: String) {
        binding?.etEmailSignin?.error = error
    }

    override fun setPasswordError(error: String) {
        binding?.etPasswordSignin?.error = error
    }

    override fun clearInputFields() {
        binding?.etEmailSignin?.text?.clear()
        binding?.etPasswordSignin?.text?.clear()
    }

    override fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}