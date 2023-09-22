package com.borzodelivery.android.ui.orders_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.borzodelivery.android.R
import com.borzodelivery.android.databinding.OrdersActivityBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class OrdersActivity : ComponentActivity() {

    private val viewModel by viewModels<OrdersViewModel> { OrdersViewModel.Factory }

    private lateinit var binding: OrdersActivityBinding
    private val ordersAdapter = OrdersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orders_activity)
        binding = OrdersActivityBinding.bind(findViewById(R.id.rootContainer))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ordersAdapter
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.onRefreshRequested() }

        lifecycleScope.launch {
            viewModel.uiState.collect {
                refreshView(it)
            }
        }
    }

    private fun refreshView(state: OrdersUiState) {
        binding.toolbar.setTitle(state.screenTitle)
        binding.swipeRefreshLayout.isRefreshing = state.listState.isRefreshing
        ordersAdapter.items = state.listState.listItems

        refreshLoadingError(state.listState.loadingErrorId, state.listState.loadingErrorText)
    }

    private var previousDisplayedSnackbarId: Int? = null

    private fun refreshLoadingError(snackbarId: Int, @StringRes snackbarText: Int? = null) {
        if (snackbarText != null && previousDisplayedSnackbarId != snackbarId) {
            previousDisplayedSnackbarId = snackbarId
            Snackbar.make(binding.rootContainer, snackbarText, Snackbar.LENGTH_SHORT).show()
        }
    }

}
