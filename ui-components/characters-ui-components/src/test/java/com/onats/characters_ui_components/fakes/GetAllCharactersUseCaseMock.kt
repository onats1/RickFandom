package com.onats.characters_ui_components.fakes

import com.onats.common.DomainResult
import com.onats.core_character.models.Character
import com.onats.core_character.usecases.GetAllCharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllCharactersUseCaseMock: GetAllCharactersUseCase {
    override suspend fun invoke(): Flow<DomainResult<List<Character>>> = flow {
        emit(
            DomainResult.Success<List<Character>>(
                data = listOf()
            )
        )
    }
}