package com.example.moviezone.screen.sign_in

import android.os.Bundle
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

class SignInFragment: Fragment() {

    private lateinit var binding: SignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.sign_in, container, false)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]

        binding.ivBackButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.navigateFromSignInToWelcome)
        }

        binding.btnSignInSignin.setOnClickListener {
            viewModel.signInWithEmailAndPassword(binding.etEmailSignin.text.toString(),
                                                 binding.etPasswordSignin.text.toString())

            binding.etEmailSignin.text.clear()
            binding.etPasswordSignin.text.clear()
        }

        return binding.root
    }
}