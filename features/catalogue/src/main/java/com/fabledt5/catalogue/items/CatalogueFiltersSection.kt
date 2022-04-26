package com.fabledt5.catalogue.items

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.catalogue.R
import com.fabledt5.common.theme.DarkLateBlack
import com.fabledt5.common.theme.DefaultHorizontalGradient
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.utils.gradient

@Composable
fun CatalogueFiltersSection() {
    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxSize()
    ) {
        DevelopersFilter(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
        )
        PlatformsFilter(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
        )
        GenresFilter(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun DevelopersFilter(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.by_developer).uppercase(),
            modifier = Modifier.padding(start = 10.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(5) {
            var isSelected by remember { mutableStateOf(false) }
            FilterItem(
                modifier = Modifier.fillMaxWidth(fraction = .3f),
                isActive = isSelected,
                onItemClick = { isSelected = !isSelected }
            )
            if (it < 5) Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun PlatformsFilter(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.by_platform).uppercase(),
            modifier = Modifier.padding(start = 10.dp, bottom = 15.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            items(5) {
                var isSelected by remember { mutableStateOf(false) }
                FilterItem(
                    modifier = Modifier.fillMaxWidth(fraction = .3f),
                    isActive = isSelected,
                    onItemClick = { isSelected = !isSelected }
                )
                if (it < 5) Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
fun GenresFilter(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.by_genre).uppercase(),
            modifier = Modifier.padding(start = 10.dp, bottom = 15.dp),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun FilterItem(modifier: Modifier = Modifier, onItemClick: () -> Unit, isActive: Boolean) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) Color.Black else DarkLateBlack
    )
    val borderColor by animateColorAsState(
        targetValue = if (isActive) Color.White else Color.DarkGray
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size = 8.dp))
            .background(backgroundColor)
            .then(
                if (isActive) Modifier.border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = borderColor
                    ),
                    shape = RoundedCornerShape(size = 8.dp)
                ) else Modifier
            )
            .clickable { onItemClick() },
    ) {
        Text(
            text = "Ubisoft".uppercase(),
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 15.dp)
                .align(Alignment.Center)
                .then(if (isActive) Modifier.gradient(DefaultHorizontalGradient) else Modifier),
            color = if (!isActive) Color.White.copy(alpha = .3f) else Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}