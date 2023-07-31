package com.borzodelivery.android.data.order.remote

import com.google.gson.annotations.SerializedName
import com.borzodelivery.android.data.order.local.Order

data class OrderDto(
    @SerializedName("order_id") val orderId: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("text") val text: String?
)

fun OrderDto.toEntity() = Order(
    orderId = orderId!!,
    address = address!!,
    date = date!!,
    text = text!!
)
