package com.onats.characters_ui_components.fakes

import com.onats.common.DomainResult
import com.onats.core_character.models.Character
import com.onats.core_character.usecases.SearchCharactersUseCase
import com.onats.core_character.usecases.SearchQueryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchCharactersUseCaseMock: SearchCharactersUseCase {
    override suspend fun invoke(request: SearchQueryRequest): Flow<DomainResult<List<Character>>> = flow {
        emit(
            DomainResult.Success<List<Character>>(
                data = listOf()
            )
        )
    }
}