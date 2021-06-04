package com.example.drinkbrowserapp.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * T is type of the view data binding
 */
class GenericViewHolder <out T : ViewDataBinding> (val binding: T) :
    RecyclerView.ViewHolder(binding.root)