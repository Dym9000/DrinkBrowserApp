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
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.FragmentDisplayListBinding
import com.example.drinkbrowserapp.ui.common.ItemTopSpacing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentDisplayListBinding
    private lateinit var recyclerViewAdapter: SearchListAdapter
    private var requestManager: RequestManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_display_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        searchViewModel.setDrinkNameQuery("white")

        setGlide()
        setRecyclerView()
        setObservers()

        return binding.root

    }

    private fun setGlide() {
        val requestOptions = RequestOptions
            .placeholderOf(R.drawable.ic_baseline_hourglass_top_24)
            .error(R.drawable.ic_baseline_no_drinks_24)

        activity?.let {
            requestManager = Glide.with(it)
                .applyDefaultRequestOptions(requestOptions)
        }
    }

    private fun setRecyclerView() {
        recyclerViewAdapter = SearchListAdapter(requestManager as RequestManager)
        val manager = LinearLayoutManager(activity)
        val itemDecorationSpacing = ItemTopSpacing(30)
        binding.drinksListRecView.apply {
            layoutManager = manager
            adapter = recyclerViewAdapter
            addItemDecoration(itemDecorationSpacing)
        }
    }

    private fun setObservers() {
        searchViewModel.drinksByName.observe(viewLifecycleOwner, {

            it?.data?.let { data ->
                recyclerViewAdapter.apply {
//                    preloadImages(requestManager as RequestManager, data)
                    recyclerViewAdapter.submitList(data.toList())
                    for (item in data) {
                        Log.d("MainActivity", "${item.name} ${item.category} ${item.imageUrl}")
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.drinksListRecView.adapter = null
        requestManager = null
    }

}