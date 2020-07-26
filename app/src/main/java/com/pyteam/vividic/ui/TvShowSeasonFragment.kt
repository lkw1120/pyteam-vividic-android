package com.pyteam.vividic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.pyteam.vividic.R
import com.pyteam.vividic.databinding.FragmentTvShowSeasonBinding
import com.pyteam.vividic.ui.adapter.EpisodeListAdapter
import com.pyteam.vividic.viewmodel.TvShowSeasonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TvShowSeasonFragment : Fragment() {

    private lateinit var binding: FragmentTvShowSeasonBinding

    private val viewModel by viewModel<TvShowSeasonViewModel> {
        val safeArgs: TvShowSeasonFragmentArgs by navArgs()
        parametersOf(safeArgs.tvId,safeArgs.seasonNumber)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowSeasonBinding.inflate(inflater, container, false)
        binding.apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner

            val episodesAdapter = EpisodeListAdapter(object: EpisodeListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    Toast.makeText(requireContext(),"에피소드 id : $id",Toast.LENGTH_LONG).show()
                }
            })

            seasonEpisodes.apply {
                adapter = episodesAdapter
            }
            subscribeUi(episodesAdapter)
        }
        return binding.root
    }

    fun subscribeUi(episodesAdapter: EpisodeListAdapter) {
        viewModel.season.observe(viewLifecycleOwner, Observer {
            episodesAdapter.submitList(it.episodes)
        })
    }

}