package com.example.drinkbrowserapp.ui.scenes.favourites_tab

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.FragmentDisplayListBinding
import com.example.drinkbrowserapp.ui.common.ItemTopBottomSpacing
import com.example.drinkbrowserapp.ui.common.adapter.OnSearchItemClickListener
import com.example.drinkbrowserapp.ui.common.adapter.SearchAdapter
import com.example.drinkbrowserapp.ui.common.interfaces.UIStateListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteDrinksFragment : Fragment() {

    private val favouriteViewModel: FavouriteViewModel by viewModels()

    private lateinit var favouriteBinding: FragmentDisplayListBinding

    private var requestManager: RequestManager? = null

    private lateinit var favouriteAdapter: SearchAdapter

    private lateinit var favouriteStateListener: UIStateListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        favouriteStateListener = context as UIStateListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        favouriteBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_display_list, container, false)

        favouriteBinding.lifecycleOwner = viewLifecycleOwner

        setupActionBarWithNavController(R.id.favouriteDrinksFragment, activity as AppCompatActivity)
        setGlide()
        setRecyclerView()
        setObservers()

        return favouriteBinding.root
    }

    private fun setupActionBarWithNavController(fragmentId: Int, activity: AppCompatActivity) {
        val appBarConfiguration = AppBarConfiguration(setOf(fragmentId))
        NavigationUI.setupActionBarWithNavController(
            activity,
            findNavController(),
            appBarConfiguration
        )
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
        favouriteAdapter = SearchAdapter(requestManager as RequestManager, OnSearchItemClickListener {
            favouriteViewModel.onClick(it)
        })
        val manager = LinearLayoutManager(activity)
        val itemDecorationSpacing = ItemTopBottomSpacing(50)
        favouriteBinding.drinksListRecView.apply {
            layoutManager = manager
            adapter = favouriteAdapter
            removeItemDecoration(itemDecorationSpacing)
            addItemDecoration(itemDecorationSpacing)
        }
    }

    private fun setObservers() {
        favouriteViewModel.drinkId.observe(viewLifecycleOwner, {
            if (it != -1) {
                this.findNavController().navigate(
                    FavouriteDrinksFragmentDirections
                        .actionFavouriteDrinksFragmentToDrinkDetailsFragment3(it, R.id.searchFragment)
                )
                favouriteViewModel.onNavigated()
            }
        })
        favouriteViewModel.favouriteList.observe(viewLifecycleOwner, {
            favouriteAdapter.submitList(it.data)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favouriteBinding.drinksListRecView.adapter = null
        requestManager = null
    }

}