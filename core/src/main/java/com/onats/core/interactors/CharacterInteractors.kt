package com.onats.core.interactors

import com.onats.util.PreResolve
import kotlinx.coroutines.flow.Flow

//Identifiers for character use cases.

typealias GetAllCharactersUseCase<Data> = UseCase<Data>

typealias GetSingleCharacterUseCase<Data> = UseCase<Data>

typealias SearchCharacterUseCase<Data> = UseCase<Data>

interface UseCase<T: Any> {

    suspend fun execute(): Flow<PreResolve<T>>
}

