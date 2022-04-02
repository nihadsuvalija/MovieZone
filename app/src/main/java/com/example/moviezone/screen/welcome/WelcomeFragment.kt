package com.example.moviezone.screen.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviezone.R
import com.example.moviezone.databinding.WelcomeBinding

class WelcomeFragment: Fragment() {

    private lateinit var binding: WelcomeBinding
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome, container, false)
        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]
        return binding.root
    }
}