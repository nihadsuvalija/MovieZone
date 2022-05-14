package com.example.moviezone.screen.helpandfeedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviezone.R
import com.example.moviezone.databinding.HelpAndFeedbackBinding

class HelpAndFeedbackFragment: Fragment() {

    private var binding: HelpAndFeedbackBinding? = null
    private var viewModel: HelpAndFeedbackViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.help_and_feedback, container, false)
        viewModel = ViewModelProvider(this)[HelpAndFeedbackViewModel::class.java]

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