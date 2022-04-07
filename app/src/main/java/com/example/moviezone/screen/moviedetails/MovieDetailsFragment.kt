package com.example.moviezone.screen.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieDetailsBinding
import com.example.moviezone.model.DetailedMovie

class MovieDetailsFragment(val movieId: Int): Fragment(), MovieDetailsViewInteractor {

    private lateinit var binding: MovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details, container, false)
        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        viewModel.setViewInteractor(this)

        viewModel.getMovieById(movieId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(binding.root))
    }
}