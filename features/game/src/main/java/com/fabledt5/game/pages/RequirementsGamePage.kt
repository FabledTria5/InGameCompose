package com.fabledt5.game.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.domain.model.GameRequirements
import com.fabledt5.game.R
import com.fabledt5.game.utils.createFromHtml

@Composable
fun RequirementsGamePage(gameRequirements: GameRequirements?) {
    if (gameRequirements != null) ShowGameRequirements(requirements = gameRequirements)
    else ShowEmptyRequirements()
}

@Composable
fun ShowEmptyRequirements() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.game_requirements_error_message),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ShowGameRequirements(requirements: GameRequirements) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.minimum),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            text = requirements.min.createFromHtml(),
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
            color = Color.LightGray,
            fontFamily = Proxima
        )
        Text(
            text = stringResource(R.string.recommended),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            text = requirements.rec.createFromHtml(),
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
            color = Color.LightGray,
            fontFamily = Proxima
        )
    }
}
