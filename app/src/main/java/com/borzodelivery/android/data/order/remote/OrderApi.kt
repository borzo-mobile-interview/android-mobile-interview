package com.borzodelivery.android.data.order.remote

import retrofit2.http.GET

interface OrderApi {

    @GET("mobile-interview-api.php")
    suspend fun loadOrders(): OrderListResponse

}
