package com.example.moviezone.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.BaseBinding

class BaseFragment: Fragment(), BaseViewInteractor {

    private lateinit var binding: BaseBinding
    private lateinit var viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.base, container, false)
        viewModel = ViewModelProvider(this)[BaseViewModel::class.java]
        viewModel.setViewInteractor(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(binding.root))
    }
}