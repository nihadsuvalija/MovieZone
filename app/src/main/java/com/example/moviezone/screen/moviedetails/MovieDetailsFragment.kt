package com.example.moviezone.screen.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieDetailsBinding


class MovieDetailsFragment: Fragment(), MovieDetailsViewInteractor {

    private lateinit var binding: MovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details, container, false)
        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        viewModel.setViewInteractor(this)

        binding.ivBackButton.setOnClickListener {
            viewModel.navigateBack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(binding.root))
    }

    override fun setMoviePoster(path: String) {
        Glide.with(requireContext())
            .load(path)
            .into(binding.ivMoviePosterMoviedetails)
    }

    override fun setMovieTitle(title: String) {
        binding.tvMovieTitleMoviedetails.text = title
    }

    override fun setReleaseDate(date: String) {
        binding.tvReleaseDateMoviedetails.text = date
    }

    override fun setRuntime(runtime: String) {
        binding.tvRuntimeMoviedetails.text = runtime
    }

    override fun setGenre(genre: String) {
        binding.tvGenreMoviedetails.text = genre
    }

    override fun setBackdrop(path: String) {
        Glide.with(requireContext())
            .load(path)
            .into(binding.ivBackgroundMoviedetails)
    }

    override fun setRating(rating: String) {
        binding.tvRatingMoviedetails.text = rating
    }

    override fun setStoryLine(storyLine: String) {
        binding.tvStoryLineMoviedetails.text = storyLine
    }
}