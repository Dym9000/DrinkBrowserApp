package com.example.drinkbrowserapp.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.ActivityMainBinding
import com.example.drinkbrowserapp.ui.common.UIStateListener
import com.example.drinkbrowserapp.ui.common.displayToastMessage
import com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result.ChosenFilterResultFragment
import com.example.drinkbrowserapp.ui.scenes.common.drink_details.DrinkDetailsFragment
import com.example.drinkbrowserapp.util.*
import com.example.drinkbrowserapp.util.BottomNavController.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
    UIStateListener,
    NavGraphProvider,
    OnNavigationGraphChanged,
    OnNavigationReselectedListener {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainProgressBar: ProgressBar

    private lateinit var bottomNavigationView: BottomNavigationView

    private val bottomNavController by lazy(LazyThreadSafetyMode.NONE) {
        BottomNavController(
            this,
            R.id.nav_host_fragment,
            R.id.browser_tab_nav,
            this,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        mainProgressBar = mainBinding.progressBar

        bottomNavigationView = mainBinding.bottomNavigationView

        setupBottomNavigationBar(savedInstanceState)
        setupActionBar()
    }

    private fun setupBottomNavigationBar(savedInstanceState: Bundle?) {
        bottomNavigationView.setUpNavigation(bottomNavController, this)
        if (savedInstanceState == null) {
            bottomNavController.setupBottomNavigationBackStack(null)
            bottomNavController.onNavigationItemSelected()
        } else {
            (savedInstanceState[Constants.BOTTOM_NAV_BACKSTACK_KEY] as IntArray?)?.let { items ->
                val backstack = BackStack()
                backstack.addAll(items.toTypedArray())
                bottomNavController.setupBottomNavigationBackStack(backstack)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(
            Constants.BOTTOM_NAV_BACKSTACK_KEY,
            bottomNavController.navigationBackStack.toIntArray()
        )

    }

    override fun onDataStateChanged(dataState: DataState<*>?) {
        dataState?.let {
            GlobalScope.launch(Dispatchers.Main) {
                when (it.state) {
                    DataStateType.SUCCESS -> displayProgressBar(false)
                    DataStateType.LOADING -> displayProgressBar(true)
                    DataStateType.ERROR -> displayErrorMessage(dataState.message)
                }
            }
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(mainBinding.mainToolbar)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun displayErrorMessage(errorMessage: String?) {
        displayProgressBar(false)
        errorMessage?.let {
            displayToastMessage(it)
        }
    }

    private fun displayProgressBar(shouldBeVisible: Boolean) {
        if (shouldBeVisible) {
            mainProgressBar.visibility = View.VISIBLE
            mainBinding.navHostFragment.visibility = View.GONE
        } else {
            mainProgressBar.visibility = View.GONE
            mainBinding.navHostFragment.visibility = View.VISIBLE
        }
    }

    /**
     *  BottomNavController
     */
    override fun getNavGraphId(itemId: Int) = when (itemId) {
        R.id.browser_tab_nav -> {
            R.navigation.browser_tab_nav
        }
        R.id.search_tab_nav -> {
            R.navigation.search_tab_nav
        }
        R.id.favourites_tab_nav -> {
            R.navigation.favourites_tab_nav
        }
        else -> {
            R.navigation.browser_tab_nav
        }
    }

    override fun onGraphChange() {
        expandAppBar()
    }

    fun expandAppBar() {
        mainBinding.appBar.setExpanded(true)
    }

    override fun onReselectNavItem(
        navController: NavController,
        fragment: Fragment
    ) = when (fragment) {

        is ChosenFilterResultFragment -> {
            navController.navigate(R.id.action_chosenFilterResultFragment_to_filtersFragment)
        }

        is DrinkDetailsFragment -> {
            if (bottomNavigationView.selectedItemId == R.id.search_tab_nav) {
                navController.navigate(R.id.action_drinkDetailsFragment2_to_searchFragment)
            } else {
                navController.navigate(R.id.action_drinkDetailsFragment_to_filtersFragment)
            }
        }

        else -> {
            // do nothing
        }
    }

    /**
    /   End of BottomNavController
     */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() = bottomNavController.onBackPressed()

}