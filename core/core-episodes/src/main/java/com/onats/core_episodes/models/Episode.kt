package com.onats.core_episodes.models

data class Episode(
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<EpisodeCharacter>
)