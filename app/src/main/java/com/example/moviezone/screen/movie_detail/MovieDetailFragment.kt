package com.example.moviezone.screen.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieDetailBinding

class MovieDetailFragment: Fragment() {

    private lateinit var binding: MovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.movie_detail, container, false)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]

        return binding.root
    }
}