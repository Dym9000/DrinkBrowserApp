package com.example.drinkbrowserapp.ui.scenes.browser_tab.filters

import android.os.Bundle
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
import com.example.drinkbrowserapp.domain.FilterDomain
import com.example.drinkbrowserapp.ui.common.ItemTopSpacing
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
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

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
        recyclerViewAdapter = FiltersAdapter(requestManager as RequestManager)
        val manager = LinearLayoutManager(activity)
        val itemDecorationSpacing = ItemTopSpacing(30)
        filtersBinding.drinksListRecView.apply {
            layoutManager = manager
            adapter = recyclerViewAdapter
            addItemDecoration(itemDecorationSpacing)
        }
    }

    private fun setObservers(){
//        filtersViewModel.filters.observe(viewLifecycleOwner,{
//            filterDomainList ->
//                recyclerViewAdapter.submitList(filterDomainList)
//        })

        filtersViewModel.ingredients.observe(viewLifecycleOwner,{
            recyclerViewAdapter.submitList(listOf(FilterDomain("ingredients", it)))
        })
    }

}