package com.pyteam.vividic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
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

            (activity as AppCompatActivity).apply {
                setSupportActionBar(toolbar)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24px)
                setHasOptionsMenu(true)
            }
            val pagingAdapter = PagingStateAdapter(this@ResultFragment,2)
            viewPager.adapter = pagingAdapter

            TabLayoutMediator(tabLayout,viewPager){tab,position->
                tab.text = when(position) {
                    PAGE_MOVIE -> resources.getString(R.string.movie_results)
                    PAGE_TV_SHOW -> resources.getString(R.string.tv_show_results)
                    else -> "Error"
                }
            }.attach()

        }
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            findNavController().popBackStack()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}