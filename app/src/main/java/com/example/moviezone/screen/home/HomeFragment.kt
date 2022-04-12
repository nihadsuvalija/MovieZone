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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

/*

    TO DO: Implement Firebase Realtime Database for LIKED MOVIES!

 */

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

        binding.rvInTheatersHome.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                binding.rvInTheatersHome.parent.requestDisallowInterceptTouchEvent(true)
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

        setupInTheatersMovies()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavController(Navigation.findNavController(requireParentFragment().requireView()))
    }

    private fun setupInTheatersMovies() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val inTheatersMoviesAdapter = InTheatersMoviesAdapter()
        viewModel.getInTheaters(inTheatersMoviesAdapter)
        binding.rvInTheatersHome.layoutManager = layoutManager
        binding.rvInTheatersHome.adapter = inTheatersMoviesAdapter
    }
}