package com.example.drinkbrowserapp.ui.scenes.search_tab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.drinkbrowserapp.databinding.ItemSearchByNameBinding
import com.example.drinkbrowserapp.domain.DrinkDomain

class SearchAdapter(
    private val requestManager: RequestManager
): ListAdapter<DrinkDomain, SearchAdapter.SearchViewHolder>
    (SearchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchByNameBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchViewHolder(private val binding: ItemSearchByNameBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(item: DrinkDomain){
                binding.drinkName.text = item.name
                binding.drinkCategory.text = item.category
                binding.drinkAlcoholic.text = item.alcoholContent

                requestManager
                    .load(item.imageUrl)
                    .into(binding.drinkImage)

                binding.executePendingBindings()
            }

    }
}


class SearchDiffCallback: DiffUtil.ItemCallback<DrinkDomain>(){

    override fun areItemsTheSame(oldItem: DrinkDomain, newItem: DrinkDomain): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DrinkDomain, newItem: DrinkDomain): Boolean {
        return oldItem.name == newItem.name || oldItem.imageUrl == newItem.imageUrl
    }
}
