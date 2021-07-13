package com.onats.characters_domain.usecase

import com.onats.common.DomainResult
import com.onats.core_character.domain.CharactersRepository
import com.onats.core_character.models.Character
import com.onats.core_character.usecases.GetAllCharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetAllCharactersUseCaseImpl
@Inject
constructor(
    private val repository: CharactersRepository
) : GetAllCharactersUseCase {
    override suspend fun invoke(): Flow<DomainResult<List<Character>>> = flow {
        repository.getAllCharacters().collect { characters ->
            emit(
                characters
            )
        }
    }
}