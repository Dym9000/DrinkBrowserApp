package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.FragmentDisplayListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChosenFilterResultFragment : Fragment() {

    @Inject
    lateinit var filterResultRepository: FilterResultRepository

    private lateinit var filterResultBinding: FragmentDisplayListBinding
    private val filterResultArgs: ChosenFilterResultFragmentArgs by navArgs()
    private lateinit var filterResultAdapter: FilterResultAdapter
    val filterResultViewModel: ChosenFilterResultViewModel by viewModels {
        FilterResultViewModelFactory(
            filterResultRepository, filterResultArgs.itemName, filterResultArgs.filterName
        )
    }
    private var requestManager: RequestManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        filterResultBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_display_list, container, false
        )

        filterResultBinding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        setGlide()
        setRecyclerView()

        return filterResultBinding.root
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
        filterResultAdapter = FilterResultAdapter(requestManager as RequestManager)
        filterResultBinding.drinksListRecView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = filterResultAdapter
        }
    }
}