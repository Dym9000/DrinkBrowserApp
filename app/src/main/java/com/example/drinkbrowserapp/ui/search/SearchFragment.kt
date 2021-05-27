package com.example.drinkbrowserapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val manager = LinearLayoutManager(activity)
        binding.drinksListRecView.layoutManager = manager

        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        searchViewModel.setDrinkNameQuery("russian")
        searchViewModel.drinksByName.observe(viewLifecycleOwner,{
            Log.d("MainActivity",it.data?.size.toString())
            it.message?.let {
                Log.d("MainActivity", it)
            }
            if(it.data?.size!=null) {
                for (item in it.data){
                    Log.d("MainActivity",item.toString())
                }
            }
        })

        return binding.root

    }

}