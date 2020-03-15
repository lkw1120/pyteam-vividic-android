package com.pyteam.vividic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs

import com.pyteam.vividic.databinding.FragmentMovieDetailsBinding
import com.pyteam.vividic.ui.adapter.CastListAdapter
import com.pyteam.vividic.ui.adapter.CrewListAdapter
import com.pyteam.vividic.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    private val viewModel by viewModel<MovieDetailsViewModel> {
        val safeArgs: MovieDetailsFragmentArgs by navArgs()
        parametersOf(safeArgs.movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner
            val castAdapter = CastListAdapter()
            val crewAdapter = CrewListAdapter()
            movieCreditCastList.adapter = castAdapter
            movieCreditCrewList.adapter = crewAdapter
            subscribeUi(castAdapter)
            subscribeUi(crewAdapter)
        }
        return binding.root
    }

    private fun subscribeUi(adapter: Any) {
        viewModel.credits.observe(viewLifecycleOwner, Observer {
            when(adapter) {
                is CastListAdapter -> adapter.submitList(it.cast)
                is CrewListAdapter -> adapter.submitList(it.crew)
            }
        })
    }
}
