package com.example.moviezone.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import androidx.viewpager2.widget.ViewPager2
import com.example.moviezone.R
import com.example.moviezone.databinding.BaseBinding
import com.example.moviezone.screen.home.HomeFragment
import com.example.moviezone.screen.profile.ProfileFragment
import com.example.moviezone.screen.search.SearchFragment

class BaseFragment: Fragment(), BaseViewInteractor {

    private var binding: BaseBinding? = null
    private var viewModel: BaseViewModel? = null
    private var fragments = listOf(HomeFragment(), SearchFragment(), ProfileFragment())

    private val args: BaseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.base, container, false)
        viewModel = ViewModelProvider(this)[BaseViewModel::class.java]
        viewModel?.setViewInteractor(this)

        setupViewPager()

        binding?.bnvBase?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu_item -> binding?.vpBase?.currentItem = 0
                R.id.search_menu_item -> binding?.vpBase?.currentItem = 1
                R.id.profile_menu_item -> binding?.vpBase?.currentItem = 2
            }
            true
        }

        binding?.vpBase?.setCurrentItem(args.savedPage, false)

        binding?.vpBase?.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> binding?.bnvBase?.selectedItemId = R.id.home_menu_item
                    1 -> binding?.bnvBase?.selectedItemId = R.id.search_menu_item
                    2 -> binding?.bnvBase?.selectedItemId = R.id.profile_menu_item
                }
            }
        })

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.root?.let { Navigation.findNavController(it) }
            ?.let { viewModel?.setNavController(it) }
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this)
        adapter.setFragments(fragments)
        binding?.vpBase?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding?.vpBase?.adapter = adapter
    }
}