@file:OptIn(ExperimentalMaterialApi::class)

package com.borzodelivery.android.ui.orders_list

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OrdersLayout(
    state: OrdersUiState,
    onRefreshRequested: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            OrdersTopBar(
                screenTitle = state.screenTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
            )
            OrdersList(
                listState = state.listState,
                onRefreshRequested = onRefreshRequested,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    val errorText = state.listState.loadingErrorText?.let { stringResource(it) }
    LaunchedEffect(state.listState.loadingErrorId) {
        if (errorText != null) {
            snackbarHostState.showSnackbar(message = errorText)
        }
    }
}

@Composable
private fun OrdersTopBar(
    @StringRes screenTitle: Int,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        backgroundColor = Color.Blue,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(screenTitle),
            color = Color.White,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun OrdersList(
    listState: OrdersUiState.ListState,
    onRefreshRequested: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = listState.isRefreshing,
        onRefresh = { onRefreshRequested() }
    )
    Box(
        modifier = modifier.pullRefresh(pullRefreshState)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = listState.listItems,
                key = {
                    when (it) {
                        is OrdersUiState.ListItem.OrderItem -> it.orderId
                    }
                },
            ) {
                when (it) {
                    is OrdersUiState.ListItem.OrderItem -> OrderListItem(
                        item = it,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        PullRefreshIndicator(
            refreshing = listState.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}

@Composable
private fun OrderListItem(
    item: OrdersUiState.ListItem.OrderItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = item.orderId)
        Text(
            text = item.date,
            fontWeight = FontWeight.Bold,
        )
        Text(text = item.address)
        Text(text = item.text)
    }
}