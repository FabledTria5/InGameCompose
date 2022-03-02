package com.fabledt5.domain.use_case.home

import javax.inject.Inject

data class HomeCases @Inject constructor(
    val getHotGames: GetHotGames,
    val getUpcomingGames: GetUpcomingGames,
    val getBestGames: GetBestGames,
    val getNewGames: GetNewGames,
    val getPlatformsList: GetPlatformsList,
    val getFavoritePlatform: GetFavoritePlatform,
    val setFavoritePlatform: SetFavoritePlatform,
    val getFavoritePlatformId: GetFavoritePlatformId
)
