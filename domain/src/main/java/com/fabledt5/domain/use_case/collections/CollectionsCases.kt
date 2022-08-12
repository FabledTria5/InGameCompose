package com.fabledt5.domain.use_case.collections

import com.fabledt5.domain.use_case.game.MarkAsPlayed
import javax.inject.Inject

data class CollectionsCases @Inject constructor(
    val getGamesByDate: GetGamesByDate,
    val getFavoriteGames: GetFavoriteGames,
    val getPlayedGames: GetPlayedGames,
    val markAsFavorite: MarkAsFavorite,
    val markAsPlayed: MarkAsPlayed,
)
