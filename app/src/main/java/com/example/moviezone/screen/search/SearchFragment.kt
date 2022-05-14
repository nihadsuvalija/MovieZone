package com.example.moviezone.screen.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviezone.R
import com.example.moviezone.databinding.SearchBinding
import com.example.moviezone.model.SearchedMovie
import com.example.moviezone.screen.home.HomeFragment
import com.example.moviezone.screen.home.HomeViewModel
import com.example.moviezone.screen.main.MainActivity

class SearchFragment: Fragment(), SearchViewInteractor {

    private lateinit var binding: SearchBinding
    private var viewModel: SearchViewModel? = null

    private var searchItemAdapter = SearchItemAdapter()
    private var savedSearchString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = parentFragment?.activity as MainActivity
        val fragmentState = activity.getFragmentState()
        if (fragmentState?.isEmpty == false) {
            savedSearchString = fragmentState?.getString("lastSearch").toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search, container, false)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel?.setViewInteractor(this)
        searchItemAdapter.setViewModelInteractor(this.viewModel)

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

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() == "") {
                    setNoResults()
                } else {
                    viewModel?.searchByTitle(p0.toString())
                    savedSearchString = p0.toString()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.etSearchSearch.setText(savedSearchString)

        setupMovies()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.setNavController(Navigation.findNavController(requireParentFragment().requireView()))
        if (savedInstanceState != null) {
            savedSearchString = savedInstanceState.getString("savedSearchString").toString()
            // SpannableStringBuilder creates editable from string
            binding.etSearchSearch.text = SpannableStringBuilder(savedSearchString)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val activity = parentFragment?.activity as MainActivity
        savedSearchString = binding.etSearchSearch.text.toString()
        val fragmentState = Bundle()
        fragmentState.putString("lastSearch", savedSearchString)
        activity.saveFragmentState(fragmentState)
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
        if (movies.isEmpty()) setNoResults()
    }

    override fun setNoResults() {
        binding.rvSearchedItemsSearch.visibility = View.GONE
        binding.ivNoResultsSearch.visibility = View.VISIBLE
        binding.tvNoResultsSearch.visibility = View.VISIBLE
    }

    override fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}