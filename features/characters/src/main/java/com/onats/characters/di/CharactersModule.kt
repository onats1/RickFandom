package com.onats.characters.di

import com.onats.characters.BuildConfig
import com.onats.characters_domain.repository.CharactersRepositoryImpl
import com.onats.characters_domain.usecase.GetAllCharactersUseCaseImpl
import com.onats.characters_domain.usecase.SearchCharactersUseCaseImpl
import com.onats.characters_remote.data.CharactersRemoteDataSourceImpl
import com.onats.characters_remote.mappers.CharacterMapperImpl
import com.onats.characters_remote.mappers.LocationMapperImpl
import com.onats.characters_remote.mappers.OriginMapperImpl
import com.onats.common.network.NetworkModule
import com.onats.core_android_character.CharacterMapper
import com.onats.core_android_character.LocationMapper
import com.onats.core_android_character.OriginMapper
import com.onats.core_android_character.data.CharactersApiService
import com.onats.core_character.data.CharactersRemoteDataSource
import com.onats.core_character.domain.CharactersRepository
import com.onats.core_character.usecases.GetAllCharactersUseCase
import com.onats.core_character.usecases.SearchCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CharactersModule {

    @[Provides Singleton]
    fun provideCharacterApiService(): CharactersApiService = NetworkModule.retrofitClient(
        serviceClass = CharactersApiService::class.java,
        baseUrl = BuildConfig.BASE_URL,
        converterFactory = MoshiConverterFactory.create()
    )

    @[Provides Singleton]
    fun provideGetAllCharactersUseCase(charactersRepository: CharactersRepository): GetAllCharactersUseCase {
        return GetAllCharactersUseCaseImpl(charactersRepository)
    }

    @[Provides Singleton]
    fun provideCharacterRepository(charactersDataSource: CharactersRemoteDataSource): CharactersRepository {
        return CharactersRepositoryImpl(charactersDataSource)
    }

    @[Provides Singleton]
    fun provideCharacterDataSource(
        charactersApiService: CharactersApiService,
        characterMapper: CharacterMapper
    ): CharactersRemoteDataSource {
        return CharactersRemoteDataSourceImpl(characterMapper, charactersApiService)
    }

    @[Provides Singleton]
    fun provideCharacterMapper(
        originMapper: OriginMapper,
        locationMapper: LocationMapper
    ): CharacterMapper = CharacterMapperImpl(originMapper, locationMapper)

    @[Provides Singleton]
    fun provideCharacterQueryUseCase(
        charactersRepository: CharactersRepository
    ): SearchCharactersUseCase = SearchCharactersUseCaseImpl(charactersRepository)


    @[Provides Singleton]
    fun provideLocationMapper(): LocationMapper = LocationMapperImpl()

    @[Provides Singleton]
    fun provideOriginMapper(): OriginMapper = OriginMapperImpl()

}