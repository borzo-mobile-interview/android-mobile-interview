package com.borzodelivery.android.data.order.remote

import com.google.gson.annotations.SerializedName

data class OrderListResponse(
    @SerializedName("orders") val orders: List<OrderDto>?
)
