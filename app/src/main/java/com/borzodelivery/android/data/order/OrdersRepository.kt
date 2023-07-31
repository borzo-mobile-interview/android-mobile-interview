package com.borzodelivery.android.data.order

import com.borzodelivery.android.data.order.local.Order

interface OrdersRepository {

    suspend fun loadOrders(): List<Order>

}
