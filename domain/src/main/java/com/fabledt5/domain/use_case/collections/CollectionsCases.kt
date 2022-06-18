package com.fabledt5.domain.use_case.collections

import javax.inject.Inject

data class CollectionsCases @Inject constructor(
    val getGamesByDate: GetGamesByDate
)
