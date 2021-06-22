package com.example.drinkbrowserapp.ui.scenes.search_tab

import android.app.SearchManager
import android.content.Context
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.os.IBinder
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
import com.example.drinkbrowserapp.ui.common.UIStateListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var searchView: SearchView

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var searchBinding: FragmentDisplayListBinding

    private var requestManager: RequestManager? = null

    private lateinit var searchAdapter: SearchAdapter

    private lateinit var searchStateListener: UIStateListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchStateListener = context as UIStateListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)


        searchBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_display_list, container, false)

        searchBinding.lifecycleOwner = viewLifecycleOwner

        setupActionBarWithNavController(R.id.searchFragment, activity as AppCompatActivity)
        setGlide()
        setRecyclerView()
        setObservers()

        return searchBinding.root
    }

    private fun setupActionBarWithNavController(fragmentId: Int, activity: AppCompatActivity){
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
        searchAdapter = SearchAdapter(requestManager as RequestManager, OnSearchItemClickListener {
            searchViewModel.onClick(it)
        })
        val manager = LinearLayoutManager(activity)
        val itemDecorationSpacing = ItemTopBottomSpacing(50)
        searchBinding.drinksListRecView.apply {
            layoutManager = manager
            adapter = searchAdapter
            removeItemDecoration(itemDecorationSpacing)
            addItemDecoration(itemDecorationSpacing)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        initSearchView(menu)
    }

    private fun initSearchView(menu: Menu) {
        activity?.apply {
            val searchManager: SearchManager = getSystemService(SEARCH_SERVICE) as SearchManager
            searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

            searchView.apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                maxWidth = Integer.MAX_VALUE
                setIconifiedByDefault(true)
                isSubmitButtonEnabled = true
            }
        }
        val searchTextArea = searchView.findViewById(R.id.search_src_text) as EditText

        searchTextArea.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED
                || actionId == EditorInfo.IME_ACTION_SEARCH
            ) {
                val searchQuery = textView.text.toString()
                onSearchOrFilter(searchQuery, textView)
            }
            true
        }

        // SEARCH BUTTON CLICKED (in toolbar)
        val searchButton = searchView.findViewById(R.id.search_go_btn) as View
        searchButton.setOnClickListener {
            val searchQuery = searchTextArea.text.toString()
            onSearchOrFilter(searchQuery, it)
        }

    }

    private fun onSearchOrFilter(query: String?, view: View) {
        query?.let {
            searchBinding.drinksListRecView.smoothScrollToPosition(0)
            dismissKeyboard(view.windowToken)
            searchViewModel.setQuery(query)
        }
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val inputMethodManager = activity?.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun setObservers() {
        searchViewModel.searchByNameResult.observe(viewLifecycleOwner, { searchResultList ->
            searchResultList?.data?.let {
                    searchAdapter.submitList(it)
            }
            searchStateListener.onDataStateChanged(searchResultList)
        })

        searchViewModel.drinkId.observe(viewLifecycleOwner, {
            if (it != -1) {
                this.findNavController().navigate(
                    SearchFragmentDirections
                        .actionSearchFragmentToDrinkDetailsFragment2(it, R.id.searchFragment)
                )
                searchViewModel.onNavigated()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchBinding.drinksListRecView.adapter = null
        requestManager = null
    }

}