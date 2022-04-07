package com.fabledt5.catalogue.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.catalogue.R
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.MidNightBlack

@Composable
fun CatalogueScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { CatalogueTopBar() }) {

    }
}

@Composable
fun CatalogueTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MidNightBlack)
            .padding(15.dp)
    ) {
        Text(
            text = stringResource(R.string.catalogue).uppercase(),
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = stringResource(R.string.save).uppercase(),
            modifier = Modifier.align(Alignment.CenterEnd),
            color = Color.White.copy(alpha = .5f),
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogueScreenPreview() {
    CatalogueScreen()
}