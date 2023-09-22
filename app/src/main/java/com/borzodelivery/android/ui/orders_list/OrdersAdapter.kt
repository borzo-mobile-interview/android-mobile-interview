package com.borzodelivery.android.ui.orders_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.borzodelivery.android.databinding.OrderViewHolderBinding

class OrdersAdapter : RecyclerView.Adapter<ViewHolder>() {

    var items: List<OrdersUiState.ListItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM_TYPE_ORDER -> {
                val binding = OrderViewHolderBinding.inflate(inflater, parent, false)
                return OrderViewHolder(binding)
            }
            else -> {
                error("Unknown item type: $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val viewType = getItemViewType(position)) {
            ITEM_TYPE_ORDER -> {
                val orderItem = items[position] as OrdersUiState.ListItem.OrderItem
                (holder as OrderViewHolder).bind(orderItem)
            }
            else -> {
                error("Unknown item type: $viewType")
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is OrdersUiState.ListItem.OrderItem -> ITEM_TYPE_ORDER
        }
    }

    companion object {
        private const val ITEM_TYPE_ORDER = 1
    }

}