package com.fabledt5.domain.use_case.game

import javax.inject.Inject

data class GameCases @Inject constructor(
    val getGameDetails: GetGameDetails,
    val getGameSnapshots: GetGameSnapshots,
    val getGameReviews: GetGameReviews,
    val markAsPlayed: MarkAsPlayed
)
