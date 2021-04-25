package com.onats.characters.framework.presentation.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.onats.core.domain.characters.Character

@Composable
fun CharactersSummaryCard(character: Character) {
    Card(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth(fraction = 0.9F)
    ) {

        Row() {
            Surface(
                modifier = Modifier.size(60.dp),
                shape = CircleShape
            ) {

            }

            Column {
                Text("Name: ${character.name}")
                Text("Gender: ${character.gender}")
                Text("Speceis: ${character.species}")
            }
        }
    }
}