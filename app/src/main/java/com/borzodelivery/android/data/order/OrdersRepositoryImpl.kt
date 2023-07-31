package com.borzodelivery.android.data.order

import com.borzodelivery.android.data.order.local.Order
import com.borzodelivery.android.data.order.remote.OrderApi
import com.borzodelivery.android.data.order.remote.toEntity

class OrdersRepositoryImpl(
    private val api: OrderApi,
) : OrdersRepository {

    override suspend fun loadOrders(): List<Order> {
        return api
            .loadOrders()
            .orders
            ?.map { it.toEntity() }
            ?: emptyList()
    }

}
