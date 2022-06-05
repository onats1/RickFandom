package com.onats.episodes_domain.usecase

import com.onats.common.DomainResult
import com.onats.core_episodes.domain.EpisodesRepository
import com.onats.core_episodes.models.Episode
import com.onats.core_episodes.usecases.GetEpisodesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEpisodesUseCaseImpl
@Inject
constructor(
    private val repository: EpisodesRepository
): GetEpisodesUseCase {
    override suspend fun invoke(): Flow<DomainResult<List<Episode>>> = flow {
        repository.getEpisodes().collectLatest {
            emit(it)
        }
    }
}