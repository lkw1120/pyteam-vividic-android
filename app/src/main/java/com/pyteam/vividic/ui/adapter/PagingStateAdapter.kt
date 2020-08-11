package com.pyteam.vividic.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pyteam.vividic.ui.ResultMovieFragment
import com.pyteam.vividic.ui.ResultTvShowFragment

const val PAGE_MOVIE = 0
const val PAGE_TV_SHOW = 1

class PagingStateAdapter(
    fragment: Fragment,
    private val itemCount: Int
): FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment = when(position) {
        PAGE_MOVIE -> ResultMovieFragment()
        PAGE_TV_SHOW -> ResultTvShowFragment()
        else -> throw Exception()
    }

    override fun getItemCount(): Int = itemCount

}