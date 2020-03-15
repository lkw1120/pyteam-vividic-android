package com.pyteam.vividic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs

import com.pyteam.vividic.databinding.FragmentTvShowDetailsBinding
import com.pyteam.vividic.ui.adapter.CastListAdapter
import com.pyteam.vividic.ui.adapter.CrewListAdapter
import com.pyteam.vividic.viewmodel.TvShowDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TvShowDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowDetailsBinding

    private val viewModel by viewModel<TvShowDetailsViewModel> {
        val safeArgs: TvShowDetailsFragmentArgs by navArgs()
        parametersOf(safeArgs.tvId)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner
            val castAdapter = CastListAdapter()
            val crewAdapter = CrewListAdapter()
            tvShowCreditCastList.adapter = castAdapter
            tvShowCreditCrewList.adapter = crewAdapter
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
