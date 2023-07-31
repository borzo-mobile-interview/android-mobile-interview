package com.borzodelivery.android.ui.orders_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.borzodelivery.android.R
import com.borzodelivery.android.data.order.OrdersRepository
import com.borzodelivery.android.data.order.local.Order
import com.borzodelivery.android.di.Injector

class OrdersViewModel(
    private val ordersRepository: OrdersRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        OrdersUiState(
            screenTitle = R.string.orders_screen_title,
            listState = OrdersUiState.ListState()
        )
    )
    private fun updateState(update: (OrdersUiState) -> OrdersUiState) = _uiState.update(update)

    val uiState: StateFlow<OrdersUiState> = _uiState.asStateFlow()

    init {
        onRefreshRequested()
    }

    fun onRefreshRequested() {
        viewModelScope.launch {
            try {
                updateState {
                    it.copy(
                        listState = it.listState.copy(
                            isRefreshing = true,
                            loadingErrorText = null,
                        )
                    )
                }
                val newOrders = ordersRepository.loadOrders()
                updateState {
                    it.copy(
                        listState = it.listState.copy(
                            isRefreshing = false,
                            listItems = newOrders.map { it.toListItem() }
                        )
                    )
                }
            } catch (e: Exception) {
                Log.e("Orders", "Failed to load orders", e)
                updateState {
                    it.copy(
                        listState = it.listState.copy(
                            isRefreshing = false,
                            loadingErrorText = R.string.orders_loading_error,
                            loadingErrorId = it.listState.loadingErrorId + 1
                        )
                    )
                }
            }
        }
    }

    private fun Order.toListItem() = OrdersUiState.ListItem.OrderItem(
        orderId = orderId,
        address = address,
        date = date,
        text = text,
    )

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
                Injector.createOrdersViewModel() as T
        }

    }

}