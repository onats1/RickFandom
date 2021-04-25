package com.onats.characters.framework.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onats.characters.R
import com.onats.characters.framework.presentation.ui.widgets.CharactersSummaryCard
import com.onats.core.domain.characters.Character

val title = R.string.characters
val defaultCharacter = Character(
    name = "Rick Sanchez",
    gender = "Male",
    species = "Human",
    isSaved = false,
    status = "Alive",
    image = ""
)

@Preview
@Composable
fun CharactersListScreen() {
    Scaffold(
        backgroundColor = Color.LightGray,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.h4,
               // modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))
            LazyColumn {
                item {
                    CharactersSummaryCard(character = defaultCharacter)
                }
            }
        }

    }
}