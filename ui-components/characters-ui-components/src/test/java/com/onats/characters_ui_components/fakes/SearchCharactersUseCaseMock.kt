package com.onats.characters_ui_components.fakes

import com.onats.common.ApplicationError
import com.onats.common.DomainResult
import com.onats.core_character.models.Character
import com.onats.core_character.usecases.SearchCharactersUseCase
import com.onats.core_character.usecases.SearchQueryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchCharactersUseCaseMock: SearchCharactersUseCase {
    override suspend fun invoke(request: SearchQueryRequest): Flow<DomainResult<List<Character>>> = flow {
        when(request.query) {
            "test" -> {
                emit(
                    DomainResult.Success<List<Character>>(
                        data = listOf()
                    )
                )
            }
            "error" -> {
                emit(
                    DomainResult.Error<List<Character>>(
                        error = ApplicationError.NetworkError.defaultError
                    )
                )
            }
        }
    }
}