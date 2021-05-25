package com.example.drinkbrowserapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.drinkbrowserapp.ui.browser.BrowserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val browserViewModel: BrowserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        testRetrofit()
    }

//    private fun testRetrofit() {
//        browserViewModel.retroTest.observe(this,{
//            Log.d("MainActivity", it.toString())
//        })
//
//        browserViewModel.retroTest2.observe(this,{
//            Log.d("MainActivity", it.toString())
//        })
//    }
}