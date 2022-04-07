package com.example.moviezone.screen.base

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: BaseFragment): FragmentStateAdapter(fragment) {

    private var fragments: List<Fragment> = listOf()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun setFragments(fragments: List<Fragment>) {
        this.fragments = fragments
    }
}