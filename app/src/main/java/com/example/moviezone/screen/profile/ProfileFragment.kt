package com.example.moviezone.screen.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.ProfileBinding

class ProfileFragment: Fragment(), ProfileViewInteractor {

    private lateinit var binding: ProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile, container, false)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.setViewInteractor(this)

        binding.root.setOnClickListener {
            if(!binding.root.isFocused) {
                val imm: InputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            }
        }

        binding.btnSignOutProfile.setOnClickListener {
            viewModel.signOut()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(requireParentFragment().requireView()))
    }

    override fun setProfilePicture(imagePath: String) {
        TODO("Not yet implemented")
    }

    override fun setFullName(name: String) {
        TODO("Not yet implemented")
    }

    override fun setEmail(email: String) {
        TODO("Not yet implemented")
    }
}