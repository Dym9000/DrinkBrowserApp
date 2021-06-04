package com.example.drinkbrowserapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.drinkbrowserapp.databinding.ItemSearchByNameBinding
import com.example.drinkbrowserapp.persistence.entity.DrinkDb

class SearchListAdapter(private val requestManager: RequestManager) :
    ListAdapter<DrinkDb, SearchListAdapter.SearchByNameViewHolder>(SearchByNameDiffCallback()) {

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
        list: List<DrinkDb>
    ) {
        for (drink in list) {
            requestManager
                .load(drink.imageUrl)
                .preload()
        }
    }

    class SearchByNameViewHolder constructor(
        private val binding: ItemSearchByNameBinding,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DrinkDb) {
            binding.drinkName.text = item.name
            binding.drinkAlcoholic.text = item.alcoholContent
            binding.drinkCategory.text = item.category

            requestManager
                .load(item.imageUrl)
                .into(binding.drinkImage)

        }
    }
}

class SearchByNameDiffCallback : DiffUtil.ItemCallback<DrinkDb>() {
    override fun areItemsTheSame(oldItem: DrinkDb, newItem: DrinkDb): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DrinkDb, newItem: DrinkDb): Boolean {
        return oldItem == newItem
    }
}