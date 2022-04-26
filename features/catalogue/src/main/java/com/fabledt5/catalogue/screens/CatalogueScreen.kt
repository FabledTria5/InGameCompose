package com.fabledt5.catalogue.screens

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.catalogue.R
import com.fabledt5.catalogue.items.CatalogueFiltersSection
import com.fabledt5.catalogue.items.CatalogueSearchSection
import com.fabledt5.common.theme.*

@ExperimentalFoundationApi
@Composable
fun CatalogueScreen() {
    var isFiltersListOpen by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CatalogueTopBar(
                isFiltersOpen = isFiltersListOpen,
                onOpenFiltersClicked = { isFiltersListOpen = true },
                onSaveFiltersClicked = { isFiltersListOpen = false })
        },
        backgroundColor = Background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CatalogueSearchField(
                onSearchClicked = {},
                modifier = Modifier
                    .padding(start = 10.dp, top = 15.dp, end = 10.dp)
                    .fillMaxWidth()
            )
            if (isFiltersListOpen) CatalogueFiltersSection() else CatalogueSearchSection()
        }
    }
}

@Composable
fun CatalogueTopBar(
    isFiltersOpen: Boolean,
    onOpenFiltersClicked: () -> Unit,
    onSaveFiltersClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MidNightBlack)
            .padding(vertical = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.catalogue).uppercase(),
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontFamily = Mark,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        if (isFiltersOpen)
            TextButton(
                onClick = onSaveFiltersClicked,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(
                    text = stringResource(R.string.save).uppercase(),
                    color = Color.White.copy(alpha = .5f),
                    fontFamily = Mark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                )
            }
        else
            IconButton(
                onClick = onOpenFiltersClicked,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.icon_edit),
                    modifier = Modifier.size(20.dp),
                    tint = Color.White.copy(alpha = .5f)
                )
            }
    }
}

@Composable
fun CatalogueSearchField(modifier: Modifier = Modifier, onSearchClicked: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val spokenText = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let {
                it[0]
            }
            if (!spokenText.isNullOrEmpty()) searchQuery = spokenText
        }
    }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        modifier = modifier.shadow(elevation = 10.dp),
        leadingIcon = {
            IconButton(onClick = { onSearchClicked(searchQuery) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.icon_search)
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                    putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                    )
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
                }.let(launcher::launch)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_voice),
                    contentDescription = stringResource(R.string.icon_voice)
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            backgroundColor = DarkLateBlack,
            cursorColor = Turquoise,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            leadingIconColor = Color.White.copy(alpha = .2f),
            trailingIconColor = Color.White.copy(alpha = .4f),
        )
    )
}

@ExperimentalFoundationApi
@Preview
@Composable
fun CatalogueScreenPreview() {
    CatalogueScreen()
}