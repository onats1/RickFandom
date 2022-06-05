package com.onats.episodes.di

import com.apollographql.apollo3.ApolloClient
import com.onats.core_episodes.data.EpisodesApiService
import com.onats.core_episodes.data.EpisodesDataSource
import com.onats.core_episodes.domain.EpisodesRepository
import com.onats.core_episodes.usecases.GetEpisodesUseCase
import com.onats.episodes_domain.repository.EpisodesRepoImpl
import com.onats.episodes_domain.usecase.GetEpisodesUseCaseImpl
import com.onats.episodes_remote.EpisodesApiServiceImpl
import com.onats.episodes_remote.EpisodesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object EpisodesModule {
    @[Binds Singleton]
    fun provideGraphQLClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }
}

@[Module InstallIn(SingletonComponent::class)]
abstract class EpisodesBindingModule {

    @[Binds Singleton]
    abstract fun bindGraphQlApiService(episodesApiService: EpisodesApiServiceImpl): EpisodesApiService

    @[Binds Singleton]
    abstract fun bindEpisodesDataSource(episodesDataSourceImpl: EpisodesDataSourceImpl): EpisodesDataSource

    @[Binds Singleton]
    abstract fun bindEpisodesRepository(episodesRepoImpl: EpisodesRepoImpl): EpisodesRepository

    @[Binds Singleton]
    abstract fun bindEpisodesUseCase(episodesUseCaseImpl: GetEpisodesUseCaseImpl): GetEpisodesUseCase
}