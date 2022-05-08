package com.onats.episodes_remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.onats.core_episodes.EpisodesQuery
import com.onats.core_episodes.data.EpisodesApiService
import javax.inject.Inject

class EpisodesApiServiceImpl
@Inject
constructor(
    private val apolloClient: ApolloClient
): EpisodesApiService {
    override suspend fun getEpisodes(): ApolloResponse<EpisodesQuery.Data> {
        return apolloClient.query(EpisodesQuery()).execute()
    }
}