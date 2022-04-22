package com.example.moviezone.screen.central

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.moviezone.R
import com.example.moviezone.databinding.CentralBinding
import com.example.moviezone.screen.home.HomeFragment
import com.example.moviezone.screen.profile.ProfileFragment
import com.example.moviezone.screen.search.SearchFragment
import com.example.moviezone.utils.Const

class CentralFragment: Fragment(), CentralViewInteractor {

    private var binding: CentralBinding? = null
    private var viewModel: CentralViewModel? = null
    private var fragments: List<Fragment> = listOf(HomeFragment(), SearchFragment(), ProfileFragment())

    private val args: CentralFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.central, container, false)
        viewModel = ViewModelProvider(this)[CentralViewModel::class.java]
        viewModel?.setViewInteractor(this)

        setupViewPager()

        binding?.vpBase?.setCurrentItem(args.savedPage, false)

        when(args.savedPage) {
            Const.HOME_PAGE_INDEX -> binding?.bnvBase?.selectedItemId = R.id.home_menu_item
            Const.SEARCH_PAGE_INDEX -> binding?.bnvBase?.selectedItemId = R.id.search_menu_item
            Const.PROFILE_PAGE_INDEX -> binding?.bnvBase?.selectedItemId = R.id.profile_menu_item
        }

        binding?.bnvBase?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu_item -> binding?.vpBase?.currentItem = Const.HOME_PAGE_INDEX
                R.id.search_menu_item -> binding?.vpBase?.currentItem = Const.SEARCH_PAGE_INDEX
                R.id.profile_menu_item -> binding?.vpBase?.currentItem = Const.PROFILE_PAGE_INDEX
            }
            true
        }

        binding?.vpBase?.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    Const.HOME_PAGE_INDEX -> binding?.bnvBase?.selectedItemId = R.id.home_menu_item
                    Const.SEARCH_PAGE_INDEX -> binding?.bnvBase?.selectedItemId = R.id.search_menu_item
                    Const.PROFILE_PAGE_INDEX -> binding?.bnvBase?.selectedItemId = R.id.profile_menu_item
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

    override fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}