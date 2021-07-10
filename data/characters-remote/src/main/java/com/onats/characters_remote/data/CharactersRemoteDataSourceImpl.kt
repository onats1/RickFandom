package com.onats.characters_remote.data

import com.onats.common.ApplicationError
import com.onats.common.Result
import com.onats.core_android_character.CharacterMapper
import com.onats.core_android_character.data.CharactersApiService
import com.onats.core_character.data.CharactersRemoteDataSource
import com.onats.core_character.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharactersRemoteDataSourceImpl
@Inject
constructor(
    private val charactersMapper: CharacterMapper,
    private val charactersApiService: CharactersApiService
) : CharactersRemoteDataSource {
    override suspend fun getAllCharacters(): Flow<Result<List<Character>>> = flow {
        try {
            val networkRequest = charactersApiService.getAllCharacters()
            if (networkRequest.isSuccessful) {
                val networkResponse = networkRequest.body()
                val characters = charactersMapper.mapToDomainList(networkResponse?.results)
                emit(
                    Result.data(
                        data = characters
                    )
                )
            } else {
                emit(
                    Result.error<List<Character>>(
                        ApplicationError.NetworkError(
                            errorCode = networkRequest.code(),
                            errorMessage = "Network Error occurred."
                        )
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                Result.error<List<Character>>(
                    ApplicationError.NetworkError(
                        errorMessage = "Network Error occurred.",
                        errorException = e
                    )
                )
            )
        }
    }.flowOn(Dispatchers.IO)
}