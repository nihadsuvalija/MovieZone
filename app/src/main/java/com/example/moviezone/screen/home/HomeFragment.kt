package com.example.moviezone.screen.home

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.HomeBinding
import com.example.moviezone.model.CurrentUser
import com.example.moviezone.model.Movie
import com.example.moviezone.utils.Const
import com.google.firebase.auth.FirebaseAuth

/*
    TO DO: Implement calling API only when the screen is created, not the view!
 */

class HomeFragment: Fragment(), HomeViewInteractor {

    private var binding: HomeBinding? = null
    private var viewModel: HomeViewModel? = null

    private val movieAdapter = MovieAdapter()
    private val discoverAdapter = DiscoverAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // ViewInteractors:
        viewModel?.setViewInteractor(this)

        // ViewModelInteractors:
        movieAdapter.setViewModelInteractor(this.viewModel)
        discoverAdapter.setViewModelInteractor(this.viewModel)

        binding?.root?.setOnClickListener {
            if(binding?.root?.isFocused == false) {
                val imm: InputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding?.root?.windowToken, 0)
            }
        }

        binding?.rvMoviesHome?.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                binding?.rvMoviesHome?.parent?.requestDisallowInterceptTouchEvent(true)
                return false
            }
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        binding?.rvDiscoverHome?.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                binding?.rvDiscoverHome?.parent?.requestDisallowInterceptTouchEvent(true)
                return false
            }
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        binding?.btnNowPlayingHome?.setOnClickListener {
            viewModel?.showNowPlayingMovies()
            binding?.btnNowPlayingHome?.setTextColor(Color.parseColor(Const.THEME_BLUE_COLOR))
            binding?.btnUpcoming?.setTextColor(Color.parseColor(Const.WHITE_COLOR))
            binding?.btnTopRated?.setTextColor(Color.parseColor(Const.WHITE_COLOR))
        }

        binding?.btnUpcoming?.setOnClickListener {
            viewModel?.showUpcomingMovies()
            binding?.btnUpcoming?.setTextColor(Color.parseColor(Const.THEME_BLUE_COLOR))
            binding?.btnNowPlayingHome?.setTextColor(Color.parseColor(Const.WHITE_COLOR))
            binding?.btnTopRated?.setTextColor(Color.parseColor(Const.WHITE_COLOR))
        }

        binding?.btnTopRated?.setOnClickListener {
            viewModel?.showTopRatedMovies()
            binding?.btnTopRated?.setTextColor(Color.parseColor(Const.THEME_BLUE_COLOR))
            binding?.btnUpcoming?.setTextColor(Color.parseColor(Const.WHITE_COLOR))
            binding?.btnNowPlayingHome?.setTextColor(Color.parseColor(Const.WHITE_COLOR))
        }


        viewModel?.setProfilePhoto()

        val helloText = "Hello, " + CurrentUser.fullName
        binding?.tvHelloHome?.text = helloText

        setupMovies()
        setupDiscover()
        viewModel?.showNowPlayingMovies()
        binding?.btnNowPlayingHome?.setTextColor(Color.parseColor(Const.THEME_BLUE_COLOR))
        viewModel?.showDiscoverMovies()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.setNavController(Navigation.findNavController(requireParentFragment().requireView()))
    }

    private fun setupMovies() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.rvMoviesHome?.layoutManager = layoutManager
        binding?.rvMoviesHome?.adapter = movieAdapter
    }

    private fun setupDiscover() {
        binding?.rvDiscoverHome?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.rvDiscoverHome?.adapter = discoverAdapter
    }

    override fun setMovies(movies: List<Movie>) {
        movieAdapter.setMovies(movies)
    }

    override fun setProfilePhoto(photoUri: Uri?) {
        if (photoUri != null) {
            binding?.ivProfileImageHome?.let {
                Glide.with(requireContext())
                    .load(photoUri)
                    .into(it)
            }
        } else {
            binding?.ivProfileImageHome?.let {
                Glide.with(requireContext())
                    .load(R.drawable.ic_person)
                    .into(it)
            }
        }
    }

    override fun setDiscoverMovies(movies: List<Movie>) {
        discoverAdapter.setMovies(movies)
    }
}