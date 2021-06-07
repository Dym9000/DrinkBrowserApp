package com.example.drinkbrowserapp.ui.scenes.browser_tab.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.CarouselListItemBinding
import com.example.drinkbrowserapp.domain.FilterDomainCriteria

class SingleFilterAdapter(private val requestManager: RequestManager) :
    ListAdapter<FilterDomainCriteria, SingleFilterAdapter.SingleFilterViewHolder>(
        SingleFilterDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleFilterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarouselListItemBinding.inflate(inflater, parent, false)
        return SingleFilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SingleFilterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SingleFilterViewHolder(private val binding: CarouselListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilterDomainCriteria) {
            binding.carouselItemTitle.text = item.name

            requestManager
                .load(R.drawable.ic_launcher_background)
                .into(binding.carouselItemImage)
        }
    }
}

class SingleFilterDiffCallback : DiffUtil.ItemCallback<FilterDomainCriteria>() {
    override fun areItemsTheSame(
        oldItem: FilterDomainCriteria,
        newItem: FilterDomainCriteria
    ): Boolean {
        return oldItem.name == oldItem.name
    }

    override fun areContentsTheSame(
        oldItem: FilterDomainCriteria,
        newItem: FilterDomainCriteria
    ): Boolean {
        return oldItem.name == newItem.name
    }
}