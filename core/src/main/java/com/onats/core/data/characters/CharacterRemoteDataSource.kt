package com.onats.core.data.characters

import com.onats.core.domain.ApplicationError
import com.onats.core.domain.characters.Character
import com.onats.util.DataState
import kotlinx.coroutines.flow.Flow


interface CharacterRemoteDataSource {

    suspend fun getCharactersList(): Flow<DataState<List<Character>, ApplicationError.NetworkError>>

}