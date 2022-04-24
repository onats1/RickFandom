package com.onats.characters_domain.repository

import com.onats.common.ApplicationError
import com.onats.common.DomainResult
import com.onats.core_character.data.CharactersRemoteDataSource
import com.onats.core_character.domain.CharactersRepository
import com.onats.core_character.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl
@Inject
constructor(
    private val remoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {
    override suspend fun getAllCharacters(): Flow<DomainResult<List<Character>>> = flow {
        remoteDataSource.getAllCharacters().collect { result ->
            result.data?.let { characters ->
                emit(
                    DomainResult.Success<List<Character>>(
                        data = characters
                    )
                )
            }
            result.error.let { error ->
                if (error != ApplicationError.NoError) {
                    emit(
                        DomainResult.Error<List<Character>>(
                            error = error
                        )
                    )
                }
            }
        }
    }

    override suspend fun searchCharacters(query: String): Flow<DomainResult<List<Character>>> = flow {
        remoteDataSource.queryCharacter(query).collect { dataSourceResult ->
            dataSourceResult.data?.let { characters ->
                emit(
                    DomainResult.Success<List<Character>>(
                        data = characters
                    )
                )
            }
            dataSourceResult.error.let { error ->
                if (error != ApplicationError.NoError) {
                    emit(
                        DomainResult.Error<List<Character>>(
                            error = error
                        )
                    )
                }
            }
        }
    }
}