package com.example.moviezone.screen.moviedetails

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.MediaController
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
import com.example.moviezone.utils.Const
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerFragment


class MovieDetailsFragment: Fragment(), MovieDetailsViewInteractor {

    private lateinit var binding: MovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val castAdapter = CastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details, container, false)
        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        viewModel.setViewInteractor(this)

        viewModel.getMovieById(args.movieId)

        binding.ivBackButton.setOnClickListener {
            viewModel.navigateBack()
        }

        binding.btnWatchTrailer.setOnClickListener {
            // TO DO: Implement play the youtube video
            viewModel.launchYouTubeActivity(requireContext())
        }

        setupCast()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(binding.root))
    }

    private fun setupCast() {
        binding.rvCastMoviedetails.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCastMoviedetails.adapter = castAdapter
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
        // Subsequencing date format YYYY-MM-DD to use only YYYY
        binding.tvReleaseDateMoviedetails.text = date.subSequence(0, 4)
    }

    override fun setRuntime(runtime: String) {
        binding.tvRuntimeMoviedetails.text = runtime
    }

    override fun setGenre(genre: String) {
        var genreEdited = genre
        if (genreEdited.length > 10) {
            genreEdited = genre.subSequence(0,7).toString()
            genreEdited += "..."
        }
        binding.tvGenreMoviedetails.text = genreEdited
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

    override fun setTrailer(trailer: String) {

    }

    override fun setCast(cast: List<Cast>) {
        castAdapter.setCast(cast)
    }
}