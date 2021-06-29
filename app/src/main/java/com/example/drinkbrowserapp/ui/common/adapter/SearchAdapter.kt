package com.example.drinkbrowserapp.ui.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.drinkbrowserapp.databinding.ItemSearchByNameBinding
import com.example.drinkbrowserapp.domain.DrinkDomain

class SearchAdapter(
    private val requestManager: RequestManager,
    private val onSearchItemClickListener: OnSearchItemClickListener
) : ListAdapter<DrinkDomain, RecyclerView.ViewHolder>
    (SearchDiffCallback()) {

    fun getItemIdAtPosition(position: Int): Int{
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchByNameBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding, onSearchItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder).bind(currentList[position])
    }

    inner class SearchViewHolder(
        private val binding: ItemSearchByNameBinding,
        private val onSearchItemClickListener: OnSearchItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DrinkDomain) {
            binding.drinkName.text = item.name
            binding.drinkCategory.text = item.category
            binding.drinkAlcoholic.text = item.alcoholContent
            binding.onSearchItemClickListener = onSearchItemClickListener
            binding.drinkModel = item

            requestManager
                .load(item.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.drinkImage)

            binding.executePendingBindings()
        }
    }

}

class SearchDiffCallback : DiffUtil.ItemCallback<DrinkDomain>() {

    override fun areItemsTheSame(oldItem: DrinkDomain, newItem: DrinkDomain): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DrinkDomain, newItem: DrinkDomain): Boolean {
        return false
    }
}

class OnSearchItemClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(drink: DrinkDomain) = clickListener(drink.id)
}
