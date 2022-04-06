package com.example.moviezone.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.SearchBinding

class SearchFragment: Fragment(), SearchViewInteractor {

    private lateinit var binding: SearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search, container, false)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel.setViewInteractor(this)

        return binding.root
    }

}