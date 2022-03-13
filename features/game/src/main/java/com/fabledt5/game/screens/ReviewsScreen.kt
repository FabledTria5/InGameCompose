package com.fabledt5.game.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.Background
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack
import com.fabledt5.common.theme.Proxima
import com.fabledt5.game.R
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun ReviewsScreen() {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MidNightBlack),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.reviews).uppercase(),
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Color.White,
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(horizontal = 10.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "The Witcher 3: Wild Hunt",
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 5.dp),
                color = Color.White,
                fontFamily = Mark,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = buildAnnotatedString {
                append(
                    AnnotatedString(
                        text = stringResource(R.string.estimate),
                        spanStyle = SpanStyle(
                            color = Color.White,
                            fontSize = 13.sp,
                            fontFamily = Proxima
                        )
                    )
                )
                append(
                    AnnotatedString(
                        text = " 4.9",
                        spanStyle = SpanStyle(
                            color = Color.White,
                            fontSize = 13.sp,
                            fontFamily = Mark,
                            fontWeight = FontWeight.Bold
                        )
                    )
                )
            })
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121214)
@Composable
fun ReviewsScreenPreview() {
    ReviewsScreen()
}