package com.example.moviezone.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }
}