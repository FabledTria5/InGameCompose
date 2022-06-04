package com.example.collections.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.collections.components.CalendarComponents
import com.fabledt5.common.theme.Background
import com.fabledt5.common.theme.DimGray
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.LocalDate

@Composable
fun CalendarPage() {
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
                modifier = Modifier.fillMaxWidth(),
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
                        unSelectedColor = DimGray,
                        onDateSelected = { localDate ->

                        },
                        modifier = Modifier
                    )
                })
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121214)
@Composable
fun CalendarPagePreview() {
    CalendarPage()
}