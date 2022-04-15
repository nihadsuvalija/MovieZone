package com.example.moviezone.screen.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
import com.google.firebase.auth.FirebaseAuth

/*
    TO DO: Implement calling API only when the screen is created, not the view!
 */

class HomeFragment: Fragment(), HomeViewInteractor {

    private lateinit var binding: HomeBinding
    private lateinit var viewModel: HomeViewModel

    private val movieAdapter = MovieAdapter()

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

        binding.rvInTheatersHome.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                binding.rvInTheatersHome.parent.requestDisallowInterceptTouchEvent(true)
                return false
            }
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        binding.rvCategoriesHome.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                binding.rvCategoriesHome.parent.requestDisallowInterceptTouchEvent(true)
                return false
            }
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        binding.tvHelloHome.text = "Hello, " + CurrentUser.fullName

        if (FirebaseAuth.getInstance().currentUser?.photoUrl != null) {
            Glide.with(requireContext())
                .load(FirebaseAuth.getInstance().currentUser?.photoUrl)
                .into(binding.ivProfileImageHome)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.ic_person)
                .into(binding.ivProfileImageHome)
        }

        setupUpcomingMovies()
        setupCategories(listOf("Now Showing", "Coming Soon", "Favorites"))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(requireParentFragment().requireView()))
    }

    private fun setupUpcomingMovies() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewModel.getUpcomingMovies()
        binding.rvInTheatersHome.layoutManager = layoutManager
        binding.rvInTheatersHome.adapter = movieAdapter
    }

    private fun setupCategories(categories: List<String>) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.setCategories(categories)
        binding.rvCategoriesHome.layoutManager = layoutManager
        binding.rvCategoriesHome.adapter = categoriesAdapter
    }

    override fun setMovies(movies: List<Movie>) {
        movieAdapter.setMovies(movies)
    }
}