package com.example.drinkbrowserapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.ui.browser.BrowserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var retrofitService: DrinkService
    private val browserViewModel: BrowserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        testRetrofit()
    }

    private fun testRetrofit() {
        browserViewModel.retroTest.observe(this,{
            Log.d("MainActivity", it.toString())
        })
    }
}