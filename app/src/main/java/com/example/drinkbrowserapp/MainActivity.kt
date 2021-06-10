package com.example.drinkbrowserapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.drinkbrowserapp.databinding.ActivityMainBinding
import com.example.drinkbrowserapp.ui.common.UIStateListener
import com.example.drinkbrowserapp.ui.common.displayToastMessage
import com.example.drinkbrowserapp.util.DataState
import com.example.drinkbrowserapp.util.DataStateType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UIStateListener {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        mainProgressBar = mainBinding.progressBar

        setSupportActionBar(mainBinding.mainToolbar)
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

}