package com.example.collections.pages

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collections.R
import com.example.collections.components.CalendarComponents
import com.example.collections.components.CalendarGame
import com.example.collections.utils.toCalendarFormat
import com.fabledt5.common.components.ColorfulProgressIndicator
import com.fabledt5.common.theme.*
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.LocalDate

@ExperimentalFoundationApi
@Composable
fun CalendarPage(
    calendarGames: SnapshotStateMap<LocalDate, Resource<List<GameItem>>>,
    onDateSelected: (LocalDate) -> Unit,
    onGameClicked: (Int) -> Unit,
    onAddToPlayedClicked: (GameItem) -> Unit,
) {
    val calendarState = rememberSelectableCalendarState(
        initialSelection = listOf(LocalDate.now()),
        initialSelectionMode = SelectionMode.Multiple,
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        item {
            SelectableCalendar(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                calendarState = calendarState,
                horizontalSwipeEnabled = false,
                monthHeader = {
                    CalendarComponents.CalendarMonthHeader(
                        monthState = calendarState.monthState,
                        contentColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    )
                },
                weekHeader = { daysOfWeek ->
                    CalendarComponents.CalendarWeekHeader(daysOfWeek = daysOfWeek, color = DimGray)
                },
                dayContent = { dayState ->
                    CalendarComponents.CalendarDay(
                        dayState = dayState,
                        belongsToMonth = calendarState.monthState.currentMonth,
                        unSelectedColor = DimGray,
                        onDateSelected = onDateSelected,
                        modifier = Modifier
                    )
                })
        }
        showGames(
            gamesMap = calendarGames,
            onGameClicked = onGameClicked,
            onAddToPlayed = onAddToPlayedClicked,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.showGames(
    gamesMap: SnapshotStateMap<LocalDate, Resource<List<GameItem>>>,
    onGameClicked: (Int) -> Unit,
    onAddToPlayed: (GameItem) -> Unit,
) {
    gamesMap
        .toSortedMap(Comparator.reverseOrder())
        .forEach { (key, value) ->
            val isUpcoming = LocalDate.now() < key

            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Background)
                ) {
                    Text(
                        text = key.toCalendarFormat(),
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = Color.White,
                        fontFamily = Proxima
                    )
                }
            }
            when (value) {
                is Resource.Error -> showGamesError(value.error.errorMessage)
                is Resource.Success -> {
                    if (value.data.isNotEmpty())
                        items(items = value.data.reversed()) { game ->
                            CalendarGame(
                                gameItem = game,
                                isUpcoming = isUpcoming,
                                onGameClicked = onGameClicked,
                                onActionClicked = {
                                    onAddToPlayed(game)
                                }
                            )
                        }
                    else showGamesError()
                }
                else -> showGamesLoading()
            }
        }
}

fun LazyListScope.showGamesLoading() {
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            ColorfulProgressIndicator(modifier = Modifier.size(PROGRESS_INDICATOR_REGULAR))
        }
    }
}

fun LazyListScope.showGamesError(errorMessage: String = "") {
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = errorMessage.ifEmpty { stringResource(R.string.no_games_for_day_message) },
                color = Color.White,
                fontFamily = Mark,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
        }
    }
}