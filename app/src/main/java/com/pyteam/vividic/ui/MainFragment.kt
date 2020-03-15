package com.pyteam.vividic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.pyteam.vividic.viewmodel.MainViewModel
import com.pyteam.vividic.R
import com.pyteam.vividic.databinding.FragmentMainBinding
import com.pyteam.vividic.ui.adapter.MovieListAdapter
import com.pyteam.vividic.ui.adapter.TvShowListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner

            val moviesNowPlayingAdapter = MovieListAdapter()
            val moviesPopularAdapter = MovieListAdapter()
            val tvShowOnTheAirAdapter = TvShowListAdapter()
            val tvShowPopularAdapter = TvShowListAdapter()
            moviesNowPlayingList.adapter = moviesNowPlayingAdapter
            moviesPopularList.adapter = moviesPopularAdapter
            tvShowsOnTheAirList.adapter = tvShowOnTheAirAdapter
            tvShowsPopularList.adapter = tvShowPopularAdapter
            subscribeUi(
                moviesNowPlayingAdapter,
                moviesPopularAdapter,
                tvShowOnTheAirAdapter,
                tvShowPopularAdapter)
        }
        return binding.root
    }

    private fun subscribeUi(
        moviesNowPlayingAdapter: MovieListAdapter,
        moviesPopularAdapter: MovieListAdapter,
        tvShowOnTheAirAdapter: TvShowListAdapter,
        tvShowPopularAdapter: TvShowListAdapter
    ) {
        viewModel.apply {
            moviesNowPlaying.observe(viewLifecycleOwner, Observer {
                moviesNowPlayingAdapter.submitList(it.results)
            })
            moviesPopular.observe(viewLifecycleOwner, Observer {
                moviesPopularAdapter.submitList(it.results)
            })
            tvShowsOnTheAir.observe(viewLifecycleOwner, Observer {
                tvShowOnTheAirAdapter.submitList(it.results)
            })
            tvShowsPopular.observe(viewLifecycleOwner, Observer {
                tvShowPopularAdapter.submitList(it.results)
            })
        }
    }


}
