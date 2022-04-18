package com.example.moviezone.screen.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.SignUpBinding

class SignUpFragment: Fragment(), SignUpViewInteractor {

    private var binding: SignUpBinding? = null
    private var viewModel: SignUpViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_up, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        viewModel?.setViewInteractor(this)

        binding?.ivBackButton?.setOnClickListener {
            viewModel?.onBackPressed()
        }

        binding?.btnSignUpSignup?.setOnClickListener {
            viewModel?.registerUser(
                binding?.etFullNameSignup?.text.toString(),
                binding?.etEmailSignup?.text.toString(),
                binding?.etPasswordSignup?.text.toString()
            )
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.root?.let { Navigation.findNavController(it) }
            ?.let { viewModel?.setNavController(it) }
    }

    override fun setFullNameError(error: String) {
        binding?.etFullNameSignup?.error = error
    }

    override fun setEmailError(error: String) {
        binding?.etEmailSignup?.error = error
    }

    override fun setPasswordError(error: String) {
        binding?.etPasswordSignup?.error = error
    }

    override fun clearInputFields() {
        binding?.etFullNameSignup?.text?.clear()
        binding?.etEmailSignup?.text?.clear()
        binding?.etPasswordSignup?.text?.clear()
    }
}