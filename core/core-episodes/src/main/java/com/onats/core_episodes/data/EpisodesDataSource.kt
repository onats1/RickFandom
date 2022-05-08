package com.onats.core_episodes.data

import com.onats.common.Result
import com.onats.core_episodes.EpisodesQuery
import com.onats.core_episodes.models.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesDataSource {

    suspend fun getEpisodes(): Flow<Result<List<Episode>>>
}