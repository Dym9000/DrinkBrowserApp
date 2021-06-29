package com.example.drinkbrowserapp.ui.scenes.common.drink_details

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.domain.DrinkDomain
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("app:setIngredients")
fun formatIngredients(view: TextView, ingredients: List<DrinkDomain.Ingredient>?) {
    val result = StringBuilder()
    ingredients?.let {
        for (item in ingredients) {
            result.append("$item\n\n")
        }
        view.text = result.toString()
    }
}

@BindingAdapter("app:setMeasures")
fun formatMeasures(view: TextView, measures: List<DrinkDomain.Measure>?) {
    val result = StringBuilder()
    measures?.let {
        for (item in measures) {
            result.append("$item\n\n")
        }
        view.text = result.toString()
    }
}

@BindingAdapter("app:setImageFromUrl")
fun setImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view)
            .load(imageUrl)
            .centerInside()
            .transform(RoundedCorners(20))
            .error(R.drawable.ic_baseline_no_drinks_24)
            .placeholder(R.drawable.outline_liquor_black_48)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("app:setFavButtonColor")
fun setFavButton(view: FloatingActionButton, isFavourite: Int) {

    if (isFavourite == 0) {
        ImageViewCompat.setImageTintList(
            view,
            ColorStateList.valueOf(Color.WHITE)
        )
    }else{
        ImageViewCompat.setImageTintList(
            view,
            ColorStateList.valueOf(Color.BLACK))
    }
}