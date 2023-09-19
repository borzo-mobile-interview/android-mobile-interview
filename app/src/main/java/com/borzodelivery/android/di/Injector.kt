package com.borzodelivery.android.di

import com.borzodelivery.android.data.api.ApiBuilderImpl
import com.borzodelivery.android.data.api.ApiBuilder
import com.borzodelivery.android.data.order.OrdersRepository
import com.borzodelivery.android.data.order.OrdersRepositoryImpl
import com.borzodelivery.android.data.order.remote.FakeOrderApi
import com.borzodelivery.android.data.order.remote.OrderApi
import com.borzodelivery.android.ui.orders_list.OrdersViewModel

object Injector {

    private val apiBuilder: ApiBuilder = ApiBuilderImpl()

    private val ordersRepository: OrdersRepository = OrdersRepositoryImpl(
        api = apiBuilder.createApi(OrderApi::class.java),
        // In case you have network issues, uncomment the next line:
        // api = FakeOrderApi(),
    )

    fun createOrdersViewModel() = OrdersViewModel(ordersRepository)

}
