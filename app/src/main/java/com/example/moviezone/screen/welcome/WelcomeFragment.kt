package com.example.moviezone.screen.welcome

import android.R.attr.data
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.WelcomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task


class WelcomeFragment: Fragment(), WelcomeViewInteractor{

    private lateinit var binding: WelcomeBinding
    private lateinit var viewModel: WelcomeViewModel
    private lateinit var launcher: ActivityResultLauncher<Intent>

    val TAG = "STATUS"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(it.data)
                viewModel.handleSignInResult(task)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome, container, false)
        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]
        viewModel.setViewInteractor(this)
        viewModel.setupGoogleSignIn(requireContext())

        binding.btnSignUpWelcome.setOnClickListener {
            viewModel.onSignUpClicked()
        }

        binding.signInTxtWelcome.setOnClickListener {
            viewModel.onSignInClicked()
        }

        binding.ivGoogleWelcome.setOnClickListener {
            launcher.launch(viewModel.setupGoogleSignIn(requireContext()))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(binding.root))
    }

    override fun displayPopUp(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}