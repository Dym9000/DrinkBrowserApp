package com.example.drinkbrowserapp.ui.common

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkbrowserapp.ui.common.interfaces.CustomItemTouchHelper

class ItemTouchHelperHandler(private val itemTouchHelper: CustomItemTouchHelper)
    : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        itemTouchHelper.onSwiped(position)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

}