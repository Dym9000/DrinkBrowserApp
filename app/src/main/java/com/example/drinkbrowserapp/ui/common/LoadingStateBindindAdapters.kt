package com.example.drinkbrowserapp.ui.common

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:viewVisibility")
fun setViewVisibility(view: View, visibility: Boolean){
        view.visibility = if (visibility) View.VISIBLE else View.GONE
}