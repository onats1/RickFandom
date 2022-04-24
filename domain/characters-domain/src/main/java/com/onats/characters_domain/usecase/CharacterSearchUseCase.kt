package com.onats.characters_domain.usecase

import com.onats.common.DomainResult
import com.onats.core_character.domain.CharactersRepository
import com.onats.core_character.models.Character
import com.onats.core_character.usecases.SearchCharactersUseCase
import com.onats.core_character.usecases.SearchQueryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchCharactersUseCaseImpl
@Inject
constructor(
    private val charactersRepository: CharactersRepository
): SearchCharactersUseCase {
    override suspend fun invoke(request: SearchQueryRequest): Flow<DomainResult<List<Character>>> = flow{
        charactersRepository.searchCharacters(
            query = request.query
        ).collect {
            emit(it)
        }
    }

}