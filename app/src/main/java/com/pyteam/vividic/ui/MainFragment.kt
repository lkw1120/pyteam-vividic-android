package com.pyteam.vividic.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pyteam.vividic.viewmodel.MainViewModel
import com.pyteam.vividic.R
import com.pyteam.vividic.databinding.FragmentMainBinding
import com.pyteam.vividic.ui.adapter.ListItemDecoration
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

            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            setHasOptionsMenu(true)

            val moviesNowPlayingAdapter =
                MovieListAdapter(object : MovieListAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, id: String) {
                        findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(id)
                        )
                    }
                })

            val moviesPopularAdapter =
                MovieListAdapter(object : MovieListAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, id: String) {
                        findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(id)
                        )
                    }
                })

            val tvShowOnTheAirAdapter =
                TvShowListAdapter(object : TvShowListAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, id: String) {
                        findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToTvShowDetailsFragment(id)
                        )
                    }
                })

            val tvShowPopularAdapter =
                TvShowListAdapter(object : TvShowListAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, id: String) {
                        findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToTvShowDetailsFragment(id)
                        )
                    }
                })

            moviesNowPlaying.listBody.apply {
                addItemDecoration(
                    ListItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.item_margin_width),
                        resources.getDimensionPixelSize(R.dimen.item_margin_height)
                    )
                )
                adapter = moviesNowPlayingAdapter
            }
            moviesPopular.listBody.apply {
                addItemDecoration(
                    ListItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.item_margin_width),
                        resources.getDimensionPixelSize(R.dimen.item_margin_height)
                    )
                )
                adapter = moviesPopularAdapter
            }
            tvShowsOnTheAir.listBody.apply {
                addItemDecoration(
                    ListItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.item_margin_width),
                        resources.getDimensionPixelSize(R.dimen.item_margin_height)
                    )
                )
                adapter = tvShowOnTheAirAdapter
            }
            tvShowsPopular.listBody.apply {
                addItemDecoration(
                    ListItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.item_margin_width),
                        resources.getDimensionPixelSize(R.dimen.item_margin_height)
                    )
                )
                adapter = tvShowPopularAdapter
            }
            subscribeUi(
                moviesNowPlayingAdapter,
                moviesPopularAdapter,
                tvShowOnTheAirAdapter,
                tvShowPopularAdapter
            )

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
                moviesNowPlayingAdapter.submitList(it.movies)
            })
            moviesPopular.observe(viewLifecycleOwner, Observer {
                moviesPopularAdapter.submitList(it.movies)
            })
            tvShowsOnTheAir.observe(viewLifecycleOwner, Observer {
                tvShowOnTheAirAdapter.submitList(it.tvShows)
            })
            tvShowsPopular.observe(viewLifecycleOwner, Observer {
                tvShowPopularAdapter.submitList(it.tvShows)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_search -> {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToSearchFragment()
            )
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


}
