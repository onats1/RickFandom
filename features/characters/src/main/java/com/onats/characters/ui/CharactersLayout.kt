package com.onats.characters.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.onats.characters.R
import com.onats.common_ui.components.AppBarInfo
import com.onats.common_ui.theme.RickFandomTheme

@Composable
fun CharactersScreen() {
    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            AppBarInfo(title = stringResource(id = R.string.character))
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.character),
                textAlign = TextAlign.Center,
                fontSize = 36.sp
            )
        }
    }
}

@Preview()
@Composable
fun PreviewCharactersLayout() {
    RickFandomTheme {
        CharactersScreen()
    }
}