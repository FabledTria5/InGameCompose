package com.fabledt5.ingamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import com.fabledt5.common.theme.InGameComposeTheme
import com.fabledt5.game.GameViewModel
import com.fabledt5.ingamecompose.ui.MainScreen
import com.fabledt5.navigation.NavigationManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun gameViewModelFactory(): GameViewModel.Factory
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        mainViewModel.startUpdateSavedGames()
        setContent {
            InGameComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(navigationManager = navigationManager)
                }
            }
        }
    }

}