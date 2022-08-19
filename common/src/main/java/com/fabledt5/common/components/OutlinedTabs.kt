package com.fabledt5.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.GradinentTextStyle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun OutlinedTabs(
    pagerState: PagerState,
    tabsTitles: Array<String>,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 12.sp
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = .3f),
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.height(50.dp),
            containerColor = Color.Transparent,
            indicator = { TabRowDefaults.Indicator(color = Color.Transparent) },
            divider = { Divider(color = Color.Transparent) }
        ) {
            tabsTitles.forEachIndexed { index, tabName ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = { onTabSelected(index) },
                    modifier = Modifier
                        .background(
                            color = if (pagerState.currentPage == index)
                                Color.Black
                            else
                                Color.Transparent,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .border(
                            border = if (index == pagerState.currentPage) BorderStroke(
                                width = 1.dp,
                                color = Color.White
                            ) else
                                BorderStroke(
                                    width = 0.dp,
                                    color = Color.Transparent
                                ),
                            shape = RoundedCornerShape(size = 10.dp)
                        ),
                    text = {
                        Text(
                            text = tabName.uppercase(),
                            fontSize = textSize,
                            fontWeight = FontWeight.Bold,
                            style = GradinentTextStyle(isEnabled = index == pagerState.currentPage)
                        )
                    },
                    unselectedContentColor = Color.DarkGray,
                    selectedContentColor = Color.White
                )
            }
        }
    }
}