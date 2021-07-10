package com.onats.core_character.domain

import com.onats.common.DomainResult
import com.onats.core_character.models.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getAllCharacters(): Flow<DomainResult<List<Character>>>
}