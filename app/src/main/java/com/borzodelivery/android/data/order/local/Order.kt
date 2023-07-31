package com.borzodelivery.android.data.order.local

data class Order(
    val orderId: String,
    val address: String,
    val date: String,
    val text: String
)
