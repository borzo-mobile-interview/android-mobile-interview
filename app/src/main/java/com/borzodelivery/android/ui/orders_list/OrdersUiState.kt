package com.borzodelivery.android.ui.orders_list

import androidx.annotation.StringRes

data class OrdersUiState(
    @StringRes val screenTitle: Int,
    val listState: ListState,
) {

    data class ListState(
        val isRefreshing: Boolean = false,
        val loadingErrorId: Int = 0,
        @StringRes val loadingErrorText: Int? = null,
        val listItems: List<ListItem> = emptyList()
    )

    sealed interface ListItem {

        data class OrderItem(
            val orderId: String,
            val address: String,
            val date: String,
            val text: String,
        ) : ListItem

    }

}