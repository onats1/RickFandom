package com.onats.episodes_domain.repository

import com.onats.common.DomainResult
import com.onats.core_episodes.data.EpisodesDataSource
import com.onats.core_episodes.domain.EpisodesRepository
import com.onats.core_episodes.models.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodesRepoImpl
@Inject
constructor(
    private val episodesDataSource: EpisodesDataSource
): EpisodesRepository {
    override suspend fun getEpisodes(): Flow<DomainResult<List<Episode>>> = flow {
        episodesDataSource.getEpisodes().collectLatest { networkResult ->
            networkResult.data?.let { episodes ->
                emit(
                    DomainResult.Success<List<Episode>>(
                        data = episodes
                    )
                )
            }
            networkResult.error.let { error ->
                emit(
                    DomainResult.Error<List<Episode>>(
                        error = error
                    )
                )
            }
        }
    }
}