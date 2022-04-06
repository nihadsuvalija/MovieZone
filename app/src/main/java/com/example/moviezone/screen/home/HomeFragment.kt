package com.example.moviezone.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.HomeBinding

class HomeFragment: Fragment(), HomeViewInteractor {

    private lateinit var binding: HomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.setViewInteractor(this)

        return binding.root
    }
}