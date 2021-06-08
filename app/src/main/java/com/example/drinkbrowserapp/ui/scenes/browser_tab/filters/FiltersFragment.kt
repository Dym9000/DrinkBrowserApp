package com.example.drinkbrowserapp.ui.scenes.browser_tab.filters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.FragmentDisplayListBinding
import com.example.drinkbrowserapp.ui.common.ItemTopBottomSpacing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersFragment : Fragment() {

    private val filtersViewModel: FiltersViewModel by viewModels()
    private lateinit var filtersBinding: FragmentDisplayListBinding
    private lateinit var recyclerViewAdapter: FiltersAdapter
    private var requestManager: RequestManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        filtersBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_display_list, container, false)

        filtersBinding.lifecycleOwner = this.viewLifecycleOwner
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        setGlide()
        setRecyclerView()
        setObservers()

        return filtersBinding.root
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
        recyclerViewAdapter = FiltersAdapter(requestManager as RequestManager,
            OnSingleFilterClickListener { title, item ->
                filtersViewModel.onItemClicked(title, item)
            })
        val manager = LinearLayoutManager(activity)
        val itemDecorationSpacing = ItemTopBottomSpacing(50)
        filtersBinding.drinksListRecView.apply {
            layoutManager = manager
            adapter = recyclerViewAdapter
            removeItemDecoration(itemDecorationSpacing)
            addItemDecoration(itemDecorationSpacing)
        }
    }

    private fun setObservers() {
        filtersViewModel.filters.observe(viewLifecycleOwner, { filterDomainList ->
            recyclerViewAdapter.submitList(filterDomainList)
            recyclerViewAdapter.notifyDataSetChanged()
        })

        filtersViewModel.itemClickedName.observe(viewLifecycleOwner, { itemClickedName ->
            Log.d(
                "MainActivity",
                "ITEM $itemClickedName ${filtersViewModel.mItemClickedFilterName} CLICKED"
            )
            itemClickedName?.let {
                this.findNavController().navigate(
                    FiltersFragmentDirections
                        .actionFiltersFragmentToChosenFilterResultFragment(
                            filtersViewModel.mItemClickedFilterName, it
                        )
                )
                filtersViewModel.navigatedToClickedItem()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        filtersBinding.drinksListRecView.adapter = null
        requestManager = null
    }

}