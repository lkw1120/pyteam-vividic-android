package com.pyteam.vividic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.pyteam.vividic.databinding.FragmentPersonDetailsBinding
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
        }
        return binding.root
    }
}
