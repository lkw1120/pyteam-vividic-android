package com.pyteam.vividic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pyteam.vividic.R
import com.pyteam.vividic.databinding.FragmentResultBinding
import com.pyteam.vividic.ui.adapter.*
import com.pyteam.vividic.viewmodel.ResultViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    private val viewModel by viewModel<ResultViewModel> {
        val safeArgs: ResultFragmentArgs by navArgs()
        parametersOf(safeArgs.query)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner

            toolbar.setOnClickListener {
                findNavController().navigate(
                    ResultFragmentDirections.actionResultFragmentToSearchFragment()
                )
            }


            val movieListAdapter = MovieListAdapter(object: MovieListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        ResultFragmentDirections.actionResultFragmentToMovieDetailsFragment(id)
                    )
                }
            })
            val tvShowListAdapter = TvShowListAdapter(object: TvShowListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        ResultFragmentDirections.actionResultFragmentToTvShowDetailsFragment(id)
                    )
                }
            })
            movieResult.listBody.apply {
                addItemDecoration(
                    ItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.item_margin_width),
                        resources.getDimensionPixelSize(R.dimen.item_margin_height),
                        resources.getDimensionPixelSize(R.dimen.content_margin_width),
                        LINEAR_LAYOUT_HORIZONTAL,
                        0
                    )
                )
                adapter = movieListAdapter
            }
            tvShowResult.listBody.apply {
                addItemDecoration(
                    ItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.item_margin_width),
                        resources.getDimensionPixelSize(R.dimen.item_margin_height),
                        resources.getDimensionPixelSize(R.dimen.content_margin_width),
                        LINEAR_LAYOUT_HORIZONTAL,
                        0
                    )
                )
                adapter = tvShowListAdapter
            }
            subscribeUi(movieListAdapter,tvShowListAdapter)
        }
        return binding.root
    }

    private fun subscribeUi(
        movieListAdapter: MovieListAdapter,
        tvShowListAdapter: TvShowListAdapter
    ) {
        viewModel.apply {
            movieResult.observe(viewLifecycleOwner, Observer {
                movieListAdapter.submitList(it.movies)
            })
            tvShowResult.observe(viewLifecycleOwner, Observer {
                tvShowListAdapter.submitList(it.tvShows)
            })
        }
    }
}