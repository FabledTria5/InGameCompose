package com.fabledt5.ingamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.fabledt5.common.theme.InGameComposeTheme
import com.fabledt5.game.GameViewModel
import com.fabledt5.ingamecompose.ui.MainScreen
import com.fabledt5.navigation.NavigationManager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun gameViewModelFactory(): GameViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            InGameComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(navigationManager = navigationManager)
                }
            }
        }
    }

}