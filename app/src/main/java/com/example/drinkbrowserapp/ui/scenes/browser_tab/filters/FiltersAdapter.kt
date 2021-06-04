package com.example.drinkbrowserapp.ui.scenes.browser_tab.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.drinkbrowserapp.databinding.CarouselListBinding
import com.example.drinkbrowserapp.domain.FilterDomain

class FiltersAdapter(private val requestManager: RequestManager)
    : ListAdapter<FilterDomain, FiltersAdapter.FiltersViewHolder>(FiltersDiffCallback()){

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CarouselListBinding.inflate(layoutInflater, parent, false)
        return FiltersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FiltersViewHolder(private val binding: CarouselListBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: FilterDomain){
            val childLayoutManager = LinearLayoutManager(
                binding.carouselRecView.context, RecyclerView.HORIZONTAL, false)

            childLayoutManager.initialPrefetchItemCount = 100

            binding.carouselListTitle.text = item.name
            binding.carouselRecView.apply {
                adapter = SingleFilterAdapter(requestManager).apply {
                    submitList(item.filterDomainCriteria.data)
                }
                layoutManager = childLayoutManager
                setRecycledViewPool(viewPool)
            }
        }
    }
}

class FiltersDiffCallback: DiffUtil.ItemCallback<FilterDomain>(){
    override fun areItemsTheSame(oldItem: FilterDomain, newItem: FilterDomain): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: FilterDomain, newItem: FilterDomain): Boolean {
        return oldItem.filterDomainCriteria.data == newItem.filterDomainCriteria.data
    }
}