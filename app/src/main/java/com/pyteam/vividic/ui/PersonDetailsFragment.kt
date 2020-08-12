package com.pyteam.vividic.ui

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.pyteam.vividic.R
import com.pyteam.vividic.databinding.FragmentPersonDetailsBinding
import com.pyteam.vividic.ui.adapter.ItemDecorator
import com.pyteam.vividic.ui.adapter.LINEAR_LAYOUT_HORIZONTAL
import com.pyteam.vividic.ui.adapter.MovieListAdapter
import com.pyteam.vividic.ui.adapter.TvShowListAdapter
import com.pyteam.vividic.ui.listener.AppBarStateChangeListener
import com.pyteam.vividic.viewmodel.PersonDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PersonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPersonDetailsBinding

    private val viewModel by viewModel<PersonDetailsViewModel> {
        val safeArgs: PersonDetailsFragmentArgs by navArgs()
        parametersOf(safeArgs.personId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        binding.apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner

            (activity as AppCompatActivity).apply {
                setSupportActionBar(toolbar)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24px)
                setHasOptionsMenu(true)
            }
            appBarLayout.addOnOffsetChangedListener(object: AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                    when(state?.name) {
                        "EXPANDED", "IDLE" -> {
                            toolbar.setTitleTextColor(Color.TRANSPARENT)
                        }
                        "COLLAPSED" -> {
                            toolbar.setTitleTextColor(Color.WHITE)
                        }
                    }
                }
            })

            val castMovieAdapter = MovieListAdapter(object: MovieListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(id))
                }
            })
            val castTvShowAdapter = TvShowListAdapter(object: TvShowListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToTvShowDetailsFragment(id))
                }
            })

            personCastMovie.apply {
                listHeader.apply {
                    listMore.visibility = View.GONE
                }
                listBody.apply {
                    addItemDecoration(
                        ItemDecorator(
                            resources.getDimensionPixelSize(R.dimen.item_margin_width),
                            resources.getDimensionPixelSize(R.dimen.item_margin_height),
                            resources.getDimensionPixelSize(R.dimen.content_margin_width),
                            LINEAR_LAYOUT_HORIZONTAL,
                            0
                        )
                    )
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.HORIZONTAL,
                        false
                    )
                    adapter = castMovieAdapter
                }
            }
            personCastTvShow.apply {
                listHeader.apply {
                    listMore.visibility = View.GONE
                }
                listBody.apply {
                    addItemDecoration(
                        ItemDecorator(
                            resources.getDimensionPixelSize(R.dimen.item_margin_width),
                            resources.getDimensionPixelSize(R.dimen.item_margin_height),
                            resources.getDimensionPixelSize(R.dimen.content_margin_width),
                            LINEAR_LAYOUT_HORIZONTAL,
                            0
                        )
                    )
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.HORIZONTAL,
                        false
                    )
                    adapter = castTvShowAdapter
                }
            }

            subscribeUi(castMovieAdapter,castTvShowAdapter)
        }
        return binding.root
    }

    private fun subscribeUi(
        castMovieAdapter: MovieListAdapter,
        castTvShowListAdapter: TvShowListAdapter) {
        viewModel.movieCredit.observe(viewLifecycleOwner, Observer {
            if(it.casts.isNotEmpty()) {
                binding.personCastMovie.root.visibility = View.VISIBLE
            }
            else {
                binding.personCastMovie.root.visibility = View.GONE
            }
            castMovieAdapter.submitList(it.casts)
        })
        viewModel.tvShowCredit.observe(viewLifecycleOwner, Observer {
            if(it.casts.isNotEmpty()) {
                binding.personCastTvShow.root.visibility = View.VISIBLE
            }
            else {
                binding.personCastTvShow.root.visibility = View.GONE
            }
            castTvShowListAdapter.submitList(it.casts)
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
            val id = viewModel.personId
            val language = requireContext().resources.configuration.locale.language
            val url = "${requireContext().getString(R.string.tmdb_web_url)}person/$id?language=$language"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}
