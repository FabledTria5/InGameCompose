package com.fabledt5.catalogue.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.catalogue.R
import com.fabledt5.common.theme.Mark

@Composable
fun CatalogueSearchSection() {
    ShowSearchSuggestions()
}

@Composable
fun ShowSearchSuggestions() {
    val suggestionsList = stringArrayResource(id = R.array.search_suggestions)
    LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(top = 15.dp)) {
        items(suggestionsList) { suggestion ->
            Box(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = suggestion.uppercase(),
                    color = Color.White.copy(alpha = .2f),
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp
                )
            }
        }
    }
}