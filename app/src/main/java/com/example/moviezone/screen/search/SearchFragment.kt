package com.example.moviezone.screen.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviezone.R
import com.example.moviezone.databinding.SearchBinding
import com.example.moviezone.model.SearchedMovie
import com.example.moviezone.screen.home.HomeFragment
import com.example.moviezone.screen.home.HomeViewModel

class SearchFragment: Fragment(), SearchViewInteractor {

    private lateinit var binding: SearchBinding
    private var viewModel: SearchViewModel? = null

    private var searchItemAdapter = SearchItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search, container, false)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel?.setViewInteractor(this)
        searchItemAdapter.setViewModelInteractor(ViewModelProvider(parentFragmentManager.fragments[0])[HomeViewModel::class.java])

        binding.root.setOnClickListener {
            if(!binding.root.isFocused) {
                val imm: InputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            }
        }

        // TO DO: Fix the bug which occurs when you delete text using a long click!
        binding.etSearchSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                println(p0.toString())
                if (p0.isNullOrBlank()) {
                    setNoResults()
                } else {
                    viewModel?.searchByTitle(p0.toString())
                }
            }

        })

        setupMovies()

        return binding.root
    }

    private fun setupMovies() {
        binding.rvSearchedItemsSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearchedItemsSearch.adapter = searchItemAdapter
    }

    override fun setMovies(movies: List<SearchedMovie>) {
        binding.rvSearchedItemsSearch.visibility = View.VISIBLE
        binding.ivNoResultsSearch.visibility = View.GONE
        binding.tvNoResultsSearch.visibility = View.GONE
        searchItemAdapter.setMovies(movies)
    }

    override fun setNoResults() {
        binding.rvSearchedItemsSearch.visibility = View.GONE
        binding.ivNoResultsSearch.visibility = View.VISIBLE
        binding.tvNoResultsSearch.visibility = View.VISIBLE
    }

}