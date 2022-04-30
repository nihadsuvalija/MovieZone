package com.example.moviezone.screen.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.MovieDetailsBinding
import com.example.moviezone.model.Cast
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.model.Review


class MovieDetailsFragment: Fragment(), MovieDetailsViewInteractor {

    private var binding: MovieDetailsBinding? = null
    private var viewModel: MovieDetailsViewModel? = null

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val castAdapter = CastAdapter()
    private val reviewAdapter = ReviewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details, container, false)
        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        viewModel?.setViewInteractor(this)

        viewModel?.getMovieById(args.movieId)

        // Setting initial visibility for Cast and Reviews:
        binding?.rvCastMoviedetails?.visibility = View.GONE
        binding?.tvCastTitleMoviedetails?.visibility = View.GONE
        binding?.rvReviewsMoviedetails?.visibility = View.GONE
        binding?.tvReviewsTitleMoviedetails?.visibility = View.GONE

        binding?.ivBackButton?.setOnClickListener {
            viewModel?.navigateBack(args.fromPage)
        }

        binding?.btnWatchVideo?.setOnClickListener {
            // TO DO: Make sure to handle YouTube screen orientation
            viewModel?.launchYouTubeActivity(requireContext())
        }

        binding?.ivAddToFavoritesMoviedetails?.setOnClickListener {
            viewModel?.addToFavorites(args.movieId)
        }

        binding?.ivAddToFavoritesMoviedetails?.isActivated = false
        for (favorite in CurrentUser.favorites) {
            if (favorite == args.movieId.toString()) {
                binding?.ivAddToFavoritesMoviedetails?.isActivated = true
            }
        }

        setupCast()
        setupReviews()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.root?.let { Navigation.findNavController(it) }
            ?.let { viewModel?.setNavController(it) }
    }

    private fun setupCast() {
        binding?.rvCastMoviedetails?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.rvCastMoviedetails?.adapter = castAdapter
    }

    private fun setupReviews() {
        binding?.rvReviewsMoviedetails?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvReviewsMoviedetails?.adapter = reviewAdapter
    }

    override fun setMoviePoster(path: String) {
        binding?.ivMoviePosterMoviedetails?.let {
            Glide.with(requireContext())
                .load(path)
                .into(it)
        }
    }

    override fun setMovieTitle(title: String) {
        binding?.tvMovieTitleMoviedetails?.text = title
    }

    override fun setReleaseDate(date: String) {
        // Subsequencing date format YYYY-MM-DD to use only YYYY
        binding?.tvReleaseDateMoviedetails?.text = date.subSequence(0, 4)
    }

    override fun setRuntime(runtime: String) {
        binding?.tvRuntimeMoviedetails?.text = runtime
    }

    override fun setGenre(genre: String) {
        var genreEdited = genre
        if (genreEdited.length > 10) {
            genreEdited = genre.subSequence(0,7).toString()
            genreEdited += "..."
        }
        binding?.tvGenreMoviedetails?.text = genreEdited
    }

    override fun setBackdrop(path: String) {
        binding?.ivBackgroundMoviedetails?.let {
            Glide.with(requireContext())
                .load(path)
                .into(it)
        }
    }

    override fun setRating(rating: String) {
        binding?.tvRatingMoviedetails?.text = rating
    }

    override fun setStoryLine(storyLine: String) {
        binding?.tvStoryLineMoviedetails?.text = storyLine
    }

    override fun setReviews(reviews: List<Review>) {
        if(reviews.isNotEmpty()) {
            binding?.rvReviewsMoviedetails?.visibility = View.VISIBLE
            binding?.tvReviewsTitleMoviedetails?.visibility = View.VISIBLE
            reviewAdapter.setReviews(reviews)
        }
    }

    override fun setCast(cast: List<Cast>) {
        if (cast.isNotEmpty()) {
            castAdapter.setCast(cast)
            binding?.tvCastTitleMoviedetails?.visibility = View.VISIBLE
            binding?.rvCastMoviedetails?.visibility = View.VISIBLE
        }
    }

    override fun setTrailer(exists: Boolean) {
        binding?.btnWatchVideo?.isEnabled = exists
    }

    override fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}