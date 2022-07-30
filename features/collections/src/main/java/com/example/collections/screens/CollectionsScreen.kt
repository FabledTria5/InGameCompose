package com.example.collections.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collections.CollectionsViewModel
import com.example.collections.R
import com.example.collections.pages.CalendarPage
import com.example.collections.pages.FavouriteGamesPage
import com.example.collections.pages.PlayedGamesPage
import com.fabledt5.common.components.OutlinedTabs
import com.fabledt5.common.theme.Background
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun CollectionsScreen(collectionsViewModel: CollectionsViewModel) {
    val collectionsTabs = stringArrayResource(id = R.array.collections_tabs)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val calendarGames = collectionsViewModel.calendarGamesMap
    val favoriteGames by collectionsViewModel.favoriteGames.collectAsState()
    val playedGames by collectionsViewModel.playedGames.collectAsState()

    var openDialog by remember { mutableStateOf(false) }

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
                    0 -> FavouriteGamesPage(
                        favoriteGames = favoriteGames,
                        onAddedToFavoritesClicked = { gameId ->
                            openDialog = true
                        }
                    )
                    1 -> PlayedGamesPage(
                        playedGames = playedGames,
                        onAddToFavoriteClicked = { game ->
                            collectionsViewModel.addGameToFavorites(game)
                        }
                    )
                    2 -> CalendarPage(
                        calendarGames = calendarGames,
                        onDateSelected = { localDate ->
                            collectionsViewModel.dateSelected(localDate)
                        },
                        onGameClicked = { gameId ->
                            collectionsViewModel.gameClicked(gameId)
                        },
                        onAddToPlayedClicked = { game ->
                            collectionsViewModel.addGameToPlayed(game)
                        }
                    )
                }
            }
        }
        if (openDialog) GameDeleteDialog() {
            openDialog = false
        }
    }
}

@Composable
fun GameDeleteDialog(onDialogDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDialogDismiss,
        buttons = {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /*TODO*/ }, modifier = Modifier.weight(weight = 1f)) {
                    Text(text = "Remove")
                }
                Spacer(modifier = Modifier.weight(.1f))
                Button(onClick = { /*TODO*/ }, modifier = Modifier.weight(weight = 1f)) {
                    Text(text = "Cancel")
                }
            }
        },
        text = {
            Text(
                text = stringResource(R.string.game_remove_question),
                color = Color.White
            )
        },
        backgroundColor = Background
    )
}