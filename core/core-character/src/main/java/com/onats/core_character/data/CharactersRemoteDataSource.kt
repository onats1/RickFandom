package com.onats.core_character.data

import com.onats.common.Result
import com.onats.core_character.models.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(): Flow<Result<List<Character>>>
}