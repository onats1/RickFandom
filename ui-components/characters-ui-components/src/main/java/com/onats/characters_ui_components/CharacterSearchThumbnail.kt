package com.onats.characters_ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.onats.common_ui.theme.RickFandomTheme
import com.onats.core_character.models.CharacterSummary

@ExperimentalMaterialApi
@Composable
fun CharacterSummaryCard(
    character: CharacterSummary,
    onCharacterClick: (CharacterSummary) -> Unit
) {
    Card(
        onClick = { onCharacterClick(character) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp, bottom = 8.dp)
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.Gray
    ) {
        Column {
            Image(
                painter = rememberImagePainter(character.image),
                contentDescription = "Character Image",
                modifier = Modifier
                    .size(128.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .background(
                        color = Color.White
                    )
                    .padding(start = 12.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.W800,
                        color = Color.Black
                    ),
                    maxLines = 1
                )
                Text(
                    text = character.species,
                    style = MaterialTheme.typography.body2.copy(color = Color.Black)
                )
                Text(
                    text = "${character.episodes} episodes",
                    style = MaterialTheme.typography.body2.copy(color = Color.Black)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewCharacterCard() {
    RickFandomTheme {
        CharacterSummaryCard(character = demoCharacter) {

        }
    }
}

val demoCharacter = CharacterSummary(
    id = 1,
    name = "Rick Sanchez",
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    episodes = 200,
    location = "Earth (Replacement Dimension)",
    gender = "Male",
    species = "Human"
)