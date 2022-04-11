package com.fabledt5.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.DefaultHorizontalGradient
import com.fabledt5.common.utils.gradient
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun OutlinedTabs(
    pagerState: PagerState,
    tabsTitles: Array<String>,
    textSize: TextUnit = 12.sp,
    onTabSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
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
            backgroundColor = Color.Transparent,
            indicator = { TabRowDefaults.Indicator(color = Color.Transparent) },
            divider = { TabRowDefaults.Divider(color = Color.Transparent) }
        ) {
            tabsTitles.forEachIndexed { index, tabName ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { onTabSelected(index) },
                    modifier = Modifier
                        .background(
                            color = if (pagerState.currentPage == index)
                                Color(0xFF0c0d0e)
                            else
                                Color.Transparent,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .border(
                            border = if (pagerState.currentPage == index) BorderStroke(
                                width = 1.dp,
                                color = Color.White
                            ) else BorderStroke(width = 0.dp, color = Color.Transparent),
                            shape = RoundedCornerShape(size = 10.dp)
                        ),
                    text = {
                        Text(
                            text = tabName.uppercase(),
                            modifier = Modifier.then(
                                if (pagerState.currentPage == index) Modifier.gradient(
                                    DefaultHorizontalGradient
                                ) else Modifier
                            ),
                            fontSize = textSize,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    unselectedContentColor = Color.White.copy(alpha = .5f)
                )
            }
        }
    }
}