package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.drinkbrowserapp.databinding.ItemSearchByFilterBinding
import com.example.drinkbrowserapp.domain.FilterSearchDomain

class FilterResultAdapter(
    private val requestManager: RequestManager,
    private val onDrinkClickListener: OnDrinkClickListener
) :
    ListAdapter<FilterSearchDomain, FilterResultAdapter.FilterResultViewHolder>(
        FilterResultDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchByFilterBinding.inflate(inflater, parent, false)
        return FilterResultViewHolder(binding, requestManager, onDrinkClickListener)
    }

    override fun onBindViewHolder(holder: FilterResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun preloadGlideImages(
        requestManager: RequestManager,
        list: List<FilterSearchDomain>
    ){
        for(item in list){
            requestManager
                .load(item.imageUrl)
                .preload()
        }
    }

    class FilterResultViewHolder(
        private val binding: ItemSearchByFilterBinding,
        private val requestManager: RequestManager,
        private val onDrinkClickListener: OnDrinkClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FilterSearchDomain) {
            binding.drinkNameFilter.text = item.drinkName
            binding.onDrinkClickListener = onDrinkClickListener
            binding.filterModel = item

            requestManager
                .load(item.imageUrl)
                .into(binding.drinkImageFilter)
            binding.executePendingBindings()
        }
    }
}

class FilterResultDiffCallback : DiffUtil.ItemCallback<FilterSearchDomain>() {
    override fun areItemsTheSame(
        oldItem: FilterSearchDomain,
        newItem: FilterSearchDomain
    ): Boolean {
        return oldItem.drinkId == newItem.drinkId
    }

    override fun areContentsTheSame(
        oldItem: FilterSearchDomain,
        newItem: FilterSearchDomain
    ): Boolean {
        return (oldItem.imageUrl == newItem.imageUrl &&
                oldItem.drinkName == newItem.drinkName)
    }
}

class OnDrinkClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(drink: FilterSearchDomain) = clickListener(drink.drinkId)
}