package com.onats.core_episodes.data

import com.onats.common.Mapper
import com.onats.core_episodes.EpisodesQuery
import com.onats.core_episodes.models.Episode
import com.onats.core_episodes.models.EpisodeCharacter

typealias EpisodesMapper = @JvmSuppressWildcards Mapper<EpisodesQuery.Episode, Episode>

typealias CharacterMapper = @JvmSuppressWildcards Mapper<EpisodesQuery.Character, EpisodeCharacter>