package com.pyteam.vividic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pyteam.vividic.R
import com.pyteam.vividic.databinding.FragmentTvShowSeasonBinding
import com.pyteam.vividic.ui.adapter.EpisodeListAdapter
import com.pyteam.vividic.ui.adapter.ItemDecorator
import com.pyteam.vividic.ui.adapter.LINEAR_LAYOUT_VERTICAL
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

            (activity as AppCompatActivity).apply {
                setSupportActionBar(toolbar)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24px)
                setHasOptionsMenu(true)
            }

            val episodesAdapter = EpisodeListAdapter(object: EpisodeListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    Toast.makeText(requireContext(),"에피소드 id : $id",Toast.LENGTH_LONG).show()
                }
            })

            seasonEpisodes.apply {
                addItemDecoration(
                    ItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.item_margin_width),
                        resources.getDimensionPixelSize(R.dimen.item_margin_height),
                        resources.getDimensionPixelSize(R.dimen.content_margin_height),
                        LINEAR_LAYOUT_VERTICAL,
                        0
                    )
                )
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL,
                    false
                )
                adapter = episodesAdapter
            }
            subscribeUi(episodesAdapter)
        }
        return binding.root
    }

    private fun subscribeUi(episodesAdapter: EpisodeListAdapter) {
        viewModel.season.observe(viewLifecycleOwner, Observer {
            episodesAdapter.submitList(it.episodes)
        })
    }

}