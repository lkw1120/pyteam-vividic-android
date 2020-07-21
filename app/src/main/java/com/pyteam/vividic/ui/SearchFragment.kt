package com.pyteam.vividic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.pyteam.vividic.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container,false)
        binding.apply {

            searchView.apply {
                isIconified = false

                setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextChange(newText: String): Boolean {
                        //Toast.makeText(context,"입력 테스트",Toast.LENGTH_LONG).show()
                        return false
                    }
                    override fun onQueryTextSubmit(query: String): Boolean {
                        findNavController().navigate(
                            SearchFragmentDirections.actionSearchFragmentToResultFragment(query)
                        )
                        return false
                    }
                })
            }
        }
        return binding.root
    }


}