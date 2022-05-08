package com.onats.core_episodes.data

import com.apollographql.apollo3.api.ApolloResponse
import com.onats.core_episodes.EpisodesQuery
import kotlinx.coroutines.flow.Flow

interface EpisodesApiService {

    suspend fun getEpisodes(): ApolloResponse<EpisodesQuery.Data>
}
