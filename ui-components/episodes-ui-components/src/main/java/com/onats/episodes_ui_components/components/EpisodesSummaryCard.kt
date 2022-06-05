package com.onats.episodes_ui_components.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onats.core_episodes.models.Episode

@[Composable ExperimentalMaterialApi]
fun EpisodesSummaryCard(
    episode: Episode,
    onEpisodeClick: (Episode) -> Unit
) {
    Card(
        onClick = { onEpisodeClick(episode) },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .testTag("episodeCard"),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        elevation = 12.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = episode.name,
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp
                )
            )
            Text(
                text = episode.episode,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400
                )
            )
            Text(
                text = "${episode.characters.size} charaters",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
    }
}

@Preview
@[Composable ExperimentalMaterialApi]
fun EpisodesSummaryCardPreview() {

    val testEpisode = remember { Episode(
        name = "Ricky test",
        episode = "S01E05",
        characters = listOf(),
        airDate = "Today"
    )}

    EpisodesSummaryCard(episode = testEpisode, onEpisodeClick = {})
}