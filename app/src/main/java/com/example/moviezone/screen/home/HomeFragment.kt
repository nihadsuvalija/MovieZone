package com.example.moviezone.screen.home

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
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.root.setOnClickListener {
            if(!binding.root.isFocused) {
                val imm: InputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            }
        }

        setupTopRatedMovies()
        setupPopularMovies()

        return binding.root
    }

    private fun setupTopRatedMovies() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val topRatedMoviesAdapter = TopRatedMoviesAdapter()
        viewModel.getTopRatedMovies(topRatedMoviesAdapter)
        binding.rvTopRatedMovies.layoutManager = layoutManager
        binding.rvTopRatedMovies.adapter = topRatedMoviesAdapter
    }

    private fun setupPopularMovies() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val popularMoviesAdapter = PopularMoviesAdapter()
        viewModel.getPopularMovies(popularMoviesAdapter)
        binding.rvPopularMovies.layoutManager = layoutManager
        binding.rvPopularMovies.adapter = popularMoviesAdapter
    }
}