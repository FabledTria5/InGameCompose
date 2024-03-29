package com.fabledt5.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(splashViewModel: SplashViewModel) {

    val isUserAuthenticated by splashViewModel.isUserAuthenticated.collectAsState()

    LaunchedEffect(key1 = true) {
        delay(timeMillis = 500)
        if (isUserAuthenticated) splashViewModel.openHomeScreen()
        else splashViewModel.openLoginScreen()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = stringResource(R.string.splash_background),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = .4f
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(R.string.application_icon),
            modifier = Modifier.size(300.dp),
            tint = Color.Unspecified,
        )
    }
}