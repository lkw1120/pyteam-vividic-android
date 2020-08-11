package com.pyteam.vividic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pyteam.vividic.R
import com.pyteam.vividic.databinding.FragmentResultPageBinding
import com.pyteam.vividic.ui.adapter.GRID_LAYOUT
import com.pyteam.vividic.ui.adapter.ItemDecorator
import com.pyteam.vividic.ui.adapter.MovieListAdapter
import com.pyteam.vividic.viewmodel.ResultViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ResultMovieFragment : Fragment() {

    private lateinit var binding: FragmentResultPageBinding

    private val viewModel by lazy {
        requireParentFragment().getViewModel<ResultViewModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResultPageBinding.inflate(inflater, container, false)
        binding.apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner

            val movieListAdapter = MovieListAdapter(object: MovieListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        ResultFragmentDirections.actionResultFragmentToMovieDetailsFragment(id)
                    )
                }
            })
            resultPageBody.apply {
                addItemDecoration(
                    ItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.item_margin_width),
                        resources.getDimensionPixelSize(R.dimen.item_margin_height),
                        resources.getDimensionPixelSize(R.dimen.content_margin_width),
                        GRID_LAYOUT,
                        3
                    )
                )
                layoutManager = GridLayoutManager(requireContext(),3)
                adapter = movieListAdapter
                subscribeUi(movieListAdapter)
            }
        }
        return binding.root
    }

    private fun subscribeUi(
        movieListAdapter: MovieListAdapter
    ) {
        viewModel.apply {
            movieResult.observe(viewLifecycleOwner, Observer {
                movieListAdapter.submitList(it.movies)
            })
        }
    }
}