package com.borzodelivery.android.ui.orders_list

import androidx.recyclerview.widget.RecyclerView
import com.borzodelivery.android.databinding.OrderViewHolderBinding

class OrderViewHolder(
    val binding: OrderViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(order: OrdersUiState.ListItem.OrderItem) {
        binding.idTextView.text = order.orderId
        binding.addressTextView.text = order.address
        binding.commentTextView.text = order.text
        binding.dateTextView.text = order.date
    }

}