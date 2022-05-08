package com.onats.core_episodes.domain

import com.onats.common.DomainResult
import com.onats.core_episodes.models.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    suspend fun getEpisodes(): Flow<DomainResult<List<Episode>>>
}