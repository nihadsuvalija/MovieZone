package com.example.moviezone.screen.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.AboutUsBinding

class AboutUsFragment: Fragment() {

    private var binding: AboutUsBinding? = null
    private var viewModel: AboutUsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.about_us, container, false)
        viewModel = ViewModelProvider(this)[AboutUsViewModel::class.java]

        binding?.ivBackButton?.setOnClickListener {
            viewModel?.onBackPressed()
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.root?.let { Navigation.findNavController(it) }
            ?.let { viewModel?.setNavController(it) }
    }
}