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
import com.pyteam.vividic.databinding.FragmentMovieDetailsBinding
import com.pyteam.vividic.ui.adapter.*
import com.pyteam.vividic.ui.listener.AppBarStateChangeListener
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


            val castAdapter = CastListAdapter(object: CastListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPersonDetailsFragment(id))
                }
            })
            val reviewAdapter = ReviewListAdapter(object: ReviewListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    val language = requireContext().resources.configuration.locale.language
                    val url = "${requireContext().getString(R.string.tmdb_web_url)}review/$id?language=$language"
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            })

            val similarAdapter = MovieListAdapter(object: MovieListAdapter.OnItemClickListener {
                override fun onItemClick(view: View, id: String) {
                    findNavController().navigate(
                        MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(id)
                    )
                }
            })

            movieCast.apply {
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
                    adapter = castAdapter
                }
            }
            movieReviews.apply {
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
                    adapter = reviewAdapter
                }
            }
            movieSimilar.apply {
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
                    adapter = similarAdapter
                }
            }
            subscribeUi(
                castAdapter,
                reviewAdapter,
                similarAdapter)
        }
        return binding.root
    }

    private fun subscribeUi(
        castAdapter: CastListAdapter,
        reviewAdapter: ReviewListAdapter,
        similarAdapter: MovieListAdapter) {
        viewModel.credits.observe(viewLifecycleOwner, Observer {
            if(it.casts.isNotEmpty()) {
                binding.movieCast.root.visibility = View.VISIBLE
            }
            else {
                binding.movieCast.root.visibility = View.GONE
            }
            castAdapter.submitList(it.casts)
        })
        viewModel.reviews.observe(viewLifecycleOwner, Observer {
            if(it.reviews.isNotEmpty()) {
                binding.movieReviews.root.visibility = View.VISIBLE
            }
            else {
                binding.movieReviews.root.visibility = View.GONE
            }
            reviewAdapter.submitList(it.reviews)
        })
        viewModel.similar.observe(viewLifecycleOwner, Observer {
            if(it.movies.isNotEmpty()) {
                binding.movieSimilar.root.visibility = View.VISIBLE
            }
            else {
                binding.movieSimilar.root.visibility = View.GONE
            }
            similarAdapter.submitList(it.movies)
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
            Toast.makeText(requireContext(),"좋아요!",Toast.LENGTH_LONG).show()
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
            Toast.makeText(requireContext(),"페이지를 이동합니다!",Toast.LENGTH_LONG).show()
            val id = viewModel.movieId
            val language = requireContext().resources.configuration.locale.language
            val url = "${requireContext().getString(R.string.tmdb_web_url)}movie/$id?language=$language"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
