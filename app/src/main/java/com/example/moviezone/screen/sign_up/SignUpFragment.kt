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

class SignUpFragment: Fragment() {

    private lateinit var binding: SignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_up, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.ivBackButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.navigateFromSignUpToWelcome)
        }

        return binding.root
    }
}