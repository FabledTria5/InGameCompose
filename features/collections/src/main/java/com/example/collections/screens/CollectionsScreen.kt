package com.example.collections.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collections.R
import com.example.collections.pages.CalendarPage
import com.example.collections.pages.FavouritesPage
import com.example.collections.pages.PlayedPage
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.common.theme.Background
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalMaterial3Api
@Composable
fun CollectionsScreen() {
    val collectionsTabs = stringArrayResource(id = R.array.collections_tabs)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MidNightBlack)
                    .padding(vertical = 15.dp)
            ) {
                Text(
                    text = stringResource(R.string.collections).uppercase(),
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        },
        containerColor = Background
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTabs(
                pagerState = pagerState,
                tabsTitles = collectionsTabs,
                onTabSelected = { padeIndex ->
                    scope.launch {
                        pagerState.animateScrollToPage(padeIndex)
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxWidth(),
            )
            HorizontalPager(
                count = collectionsTabs.size,
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { page ->
                when (page) {
                    0 -> FavouritesPage()
                    1 -> PlayedPage()
                    2 -> CalendarPage()
                }
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun CollectionsScreenPreview() {
    CollectionsScreen()
}