package com.onats.core.data.characters

import com.onats.core.characters.util.ApplicationError.NetworkError
import com.onats.core.characters.util.DataState
import com.onats.core.domain.characters.Character
import kotlinx.coroutines.flow.Flow


interface CharacterRemoteDataSource {

    suspend fun getCharactersList(): Flow<DataState<List<Character>, NetworkError>>

}