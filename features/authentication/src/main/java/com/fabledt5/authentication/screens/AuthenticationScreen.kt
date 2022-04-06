package com.fabledt5.authentication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fabledt5.authentication.AuthenticationViewModel
import com.fabledt5.authentication.R
import com.fabledt5.authentication.pages.SignInPage
import com.fabledt5.authentication.pages.SignUpPage
import com.fabledt5.common.theme.Mark
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun AuthenticationScreen(authenticationViewModel: AuthenticationViewModel) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val authenticationTabs = stringArrayResource(id = R.array.authentication_options)

    val registrationState by authenticationViewModel.registrationState.collectAsState()
    val loginState by authenticationViewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.width(200.dp),
            backgroundColor = Color.Transparent,
            indicator = { TabRowDefaults.Indicator(height = 0.dp, color = Color.Transparent) },
            divider = { TabRowDefaults.Divider(color = Color.Transparent) }
        ) {
            authenticationTabs.forEachIndexed { index, text ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    selectedContentColor = Color.Gray,
                    unselectedContentColor = Color.DarkGray
                ) {
                    Text(text = text.uppercase(), fontFamily = Mark, fontWeight = FontWeight.Bold)
                }
            }
        }
        HorizontalPager(
            count = authenticationTabs.size,
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> SignInPage(
                    onForgotPasswordClicked = { authenticationViewModel.openPasswordRecoveryScreen() },
                    onSignInClicked = { userEmail, userPassword ->
                        authenticationViewModel.authenticateUser(userEmail, userPassword)
                    },
                    signInState = loginState
                )
                1 -> SignUpPage(
                    onSignUpClicked = { userEmail, userPassword, userNickname ->
                        authenticationViewModel.registerUser(userEmail, userPassword, userNickname)
                    },
                    signUpState = registrationState
                )
            }
        }
    }
}