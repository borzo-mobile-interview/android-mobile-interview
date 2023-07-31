package com.borzodelivery.android.ui.orders_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState

class OrdersActivity : ComponentActivity() {

    private val viewModel by viewModels<OrdersViewModel> { OrdersViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrdersLayout(
                state = viewModel.uiState.collectAsState().value,
                onRefreshRequested = { viewModel.onRefreshRequested() },
            )
        }
    }
}
