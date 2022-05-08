package com.onats.episodes_remote

import com.onats.common.ApplicationError
import com.onats.common.Result
import com.onats.core_episodes.data.EpisodesApiService
import com.onats.core_episodes.data.EpisodesDataSource
import com.onats.core_episodes.data.EpisodesMapper
import com.onats.core_episodes.models.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EpisodesDataSourceImpl
@Inject
constructor(
    private val episodesMapper: EpisodesMapper,
    private val episodesApiService: EpisodesApiService
) : EpisodesDataSource {
    override suspend fun getEpisodes(): Flow<Result<List<Episode>>> = flow {
        val networkResponse = episodesApiService.getEpisodes()
        if (networkResponse.errors.isNullOrEmpty()) {
            val episodes = networkResponse.data?.episodes?.episode
            val domainEpisodes = episodesMapper.mapToDomainList(episodes?.filterNotNull())
            emit(
                Result.data<List<Episode>>(
                    data = domainEpisodes
                )
            )
        } else {
            val error = networkResponse.errors?.first()
            val message = error?.message
            emit(
                Result.error(
                    error = ApplicationError.NetworkError(
                        errorMessage = message ?: "Could not fetch episodes"
                    )
                )
            )
        }
    }.catch { exception ->
        emit(
            Result.error<List<Episode>>(
                ApplicationError.NetworkError(
                    errorException = Exception(exception),
                    errorMessage = exception.message?:"A network error occurred."
                )
            )
        )
    }.flowOn(Dispatchers.IO)
}