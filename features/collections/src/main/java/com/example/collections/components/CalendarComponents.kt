package com.example.collections.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.collections.R
import com.fabledt5.common.theme.DefaultHorizontalGradient
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.utils.capitalize
import com.fabledt5.common.utils.gradient
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

object CalendarComponents {

    @Composable
    fun CalendarMonthHeader(
        monthState: MonthState,
        contentColor: Color,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${
                    monthState.currentMonth.month.getDisplayName(
                        TextStyle.FULL,
                        Locale.ENGLISH
                    ).lowercase().capitalize()
                }, ${monthState.currentMonth.year}",
                modifier = Modifier.padding(start = 10.dp),
                color = contentColor
            )
            Row {
                IconButton(onClick = {
                    monthState.currentMonth = monthState.currentMonth.minusMonths(1)
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.icon_previous_month),
                        modifier = Modifier.size(30.dp),
                        tint = contentColor
                    )
                }
                IconButton(onClick = {
                    monthState.currentMonth = monthState.currentMonth.plusMonths(1)
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.icon_next_month),
                        modifier = Modifier.size(30.dp),
                        tint = contentColor
                    )
                }
            }
        }
    }

    @Composable
    fun CalendarWeekHeader(
        daysOfWeek: List<DayOfWeek>,
        color: Color,
        modifier: Modifier = Modifier
    ) {
        Row(modifier = modifier) {
            daysOfWeek.forEach { day ->
                Text(
                    text = day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    modifier = Modifier
                        .weight(weight = 1f)
                        .wrapContentHeight(),
                    color = color,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    fun CalendarDay(
        dayState: DayState<DynamicSelectionState>,
        belongsToMonth: YearMonth,
        unSelectedColor: Color,
        onDateSelected: (LocalDate) -> Unit,
        modifier: Modifier = Modifier
    ) {
        val date = dayState.date
        val selectionState = dayState.selectionState

        val isSelected = selectionState.isDateSelected(date)
        val isCurrentMonth = date.month.value == belongsToMonth.month.value

        ConstraintLayout(
            modifier = modifier
                .aspectRatio(ratio = 1f)
                .clickable {
                    if (isCurrentMonth) {
                        onDateSelected(date)
                        selectionState.onDateSelected(date)
                    }
                },
        ) {
            val (canvas, text) = createRefs()

            if (isSelected)
                Canvas(
                    modifier = Modifier
                        .constrainAs(canvas) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(text.top)
                        }
                        .padding(bottom = 2.dp)
                        .size(5.dp)
                ) {
                    drawCircle(brush = DefaultHorizontalGradient)
                }
            Text(
                text = date.dayOfMonth.toString(),
                modifier = Modifier
                    .constrainAs(text) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .then(
                        if (isSelected) Modifier.gradient(DefaultHorizontalGradient) else Modifier
                    ),
                color = if (isCurrentMonth) unSelectedColor else unSelectedColor.copy(alpha = .5f),
                fontFamily = Proxima
            )
        }
    }

}