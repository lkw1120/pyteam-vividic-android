package com.pyteam.vividic.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pyteam.vividic.R

import com.pyteam.vividic.databinding.FragmentTvShowDetailsBinding
import com.pyteam.vividic.ui.adapter.*
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

            (activity as AppCompatActivity).apply {
                setSupportActionBar(toolbar)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                setHasOptionsMenu(true)
            }

            val castAdapter = CastListAdapter(object: CastListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToPersonDetailsFragment(id)
                    )
                }
            })
            val reviewAdapter = ReviewListAdapter(object: ReviewListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    val language = requireContext().resources.configuration.locale.language
                    val url = "${requireContext().getString(R.string.tmdb_web_url)}review/$id?language=$language"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            })
            val seasonAdapter = SeasonListAdapter(object: SeasonListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    Toast.makeText(requireContext(),"시즌 선택",Toast.LENGTH_LONG).show()
                    findNavController().navigate(
                        TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToTvShowSeasonFragment(viewModel.tvId,id)
                    )
                }
            })
            val similarAdapter = TvShowListAdapter(object: TvShowListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentSelf(id)
                    )
                }
            })
            tvShowCast.apply {
                listHeader.apply {
                    listMore.visibility = View.GONE
                }
                listBody.apply {
                    addItemDecoration(
                        ListItemDecoration(
                            resources.getDimensionPixelSize(R.dimen.item_margin_width),
                            resources.getDimensionPixelSize(R.dimen.item_margin_height)
                        )
                    )
                    adapter = castAdapter
                }
            }
            tvShowReviews.apply {
                listHeader.apply {
                    listMore.visibility = View.GONE
                }
                listBody.apply {
                    addItemDecoration(
                        ListItemDecoration(
                            resources.getDimensionPixelSize(R.dimen.item_margin_width),
                            resources.getDimensionPixelSize(R.dimen.item_margin_height)
                        )
                    )
                    adapter = reviewAdapter
                }
            }
            tvShowSeason.apply {
                listHeader.apply {
                    listMore.visibility = View.GONE
                }
                listBody.apply {
                    addItemDecoration(
                        ListItemDecoration(
                            resources.getDimensionPixelSize(R.dimen.item_margin_width),
                            resources.getDimensionPixelSize(R.dimen.item_margin_height)
                        )
                    )
                    adapter = seasonAdapter
                }
            }
            tvShowSimilar.apply {
                listHeader.apply {
                    listMore.visibility = View.GONE
                }
                listBody.apply {
                    addItemDecoration(
                        ListItemDecoration(
                            resources.getDimensionPixelSize(R.dimen.item_margin_width),
                            resources.getDimensionPixelSize(R.dimen.item_margin_height)
                        )
                    )
                    adapter = similarAdapter
                }
            }
            subscribeUi(
                castAdapter,
                reviewAdapter,
                seasonAdapter,
                similarAdapter
            )
        }
        return binding.root
    }

    private fun subscribeUi(
        castAdapter: CastListAdapter,
        reviewAdapter: ReviewListAdapter,
        seasonAdapter: SeasonListAdapter,
        similarAdapter: TvShowListAdapter) {

        viewModel.reviews.observe(viewLifecycleOwner, Observer {
            if(it.reviews.isNotEmpty()) {
                binding.tvShowReviews.root.visibility = View.VISIBLE
            }
            else {
                binding.tvShowReviews.root.visibility = View.GONE
            }
            reviewAdapter.submitList(it.reviews)
        })
        viewModel.credits.observe(viewLifecycleOwner, Observer {
            if(it.casts.isNotEmpty()) {
                binding.tvShowCast.root.visibility = View.VISIBLE
            }
            else {
                binding.tvShowCast.root.visibility = View.GONE
            }
            castAdapter.submitList(it.casts)
        })
        viewModel.seasons.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                binding.tvShowSeason.root.visibility = View.VISIBLE
            }
            else {
                binding.tvShowSeason.root.visibility = View.GONE
            }
            seasonAdapter.submitList(it)
        })
        viewModel.similar.observe(viewLifecycleOwner, Observer {
            if(it.tvShows.isNotEmpty()) {
                binding.tvShowSimilar.root.visibility = View.VISIBLE
            }
            else {
                binding.tvShowSimilar.root.visibility = View.GONE
            }
            similarAdapter.submitList(it.tvShows)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            findNavController().popBackStack()
            true
        }
        R.id.action_favorite -> {
            Toast.makeText(requireContext(),"좋아요!", Toast.LENGTH_LONG).show()
            item.apply {
                if(isChecked) {
                    isChecked = false
                    setIcon(R.drawable.ic_favorite_border_24px)
                }
                else {
                    isChecked = true
                    setIcon(R.drawable.ic_favorite_24px)
                }
            }
            true
        }
        R.id.action_share -> {
            Toast.makeText(requireContext(),"페이지를 이동합니다!", Toast.LENGTH_LONG).show()
            val id = viewModel.tvId
            val language = requireContext().resources.configuration.locale.language
            val url = "${requireContext().getString(R.string.tmdb_web_url)}tv/$id?language=$language"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
