package com.example.drinkbrowserapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.drinkbrowserapp.databinding.ItemSearchByNameBinding
import com.example.drinkbrowserapp.network.models.DrinkRaw

class SearchListAdapter(private val requestManager: RequestManager) :
    ListAdapter<DrinkRaw, SearchListAdapter.SearchByNameViewHolder>(SearchByNameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchByNameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchByNameBinding.inflate(layoutInflater, parent, false)
        return SearchByNameViewHolder(binding, requestManager)
    }

    override fun onBindViewHolder(holder: SearchByNameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun preloadImages(
        requestManager: RequestManager,
        list: List<DrinkRaw>
    ){
        for(drink in list){
            requestManager
                .load(drink.strDrinkThumb)
                .preload()
        }
    }

    class SearchByNameViewHolder constructor(
        private val binding: ItemSearchByNameBinding,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DrinkRaw) {
            binding.drinkName.text = item.drinkName
            binding.drinkAlcoholic.text = item.strAlcoholic
            binding.drinkCategory.text = item.strCategory

            requestManager
                .load(item.strDrinkThumb)
                .into(binding.drinkImage)

        }
    }
}

class SearchByNameDiffCallback : DiffUtil.ItemCallback<DrinkRaw>() {
    override fun areItemsTheSame(oldItem: DrinkRaw, newItem: DrinkRaw): Boolean {
        return oldItem.idDrink == newItem.idDrink
    }

    override fun areContentsTheSame(oldItem: DrinkRaw, newItem: DrinkRaw): Boolean {
        return oldItem.equals(newItem)
    }
}