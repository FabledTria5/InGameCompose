package com.fabledt5.catalogue.utils

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyPagingItems<T>.isIdle() =
    loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && itemCount < 1

fun <T : Any> LazyPagingItems<T>.isLoading() = loadState.refresh is LoadState.Loading

fun <T : Any> LazyPagingItems<T>.isSuccess() =
    loadState.refresh is LoadState.NotLoading && itemCount > 0

fun <T : Any> LazyPagingItems<T>.isError() = loadState.refresh is LoadState.Error