package com.onats.core_character.usecases

import com.onats.common.UseCase
import com.onats.common.UseCaseWithParam
import com.onats.core_character.models.Character

typealias GetAllCharactersUseCase = @JvmSuppressWildcards UseCase<List<Character>>

typealias SearchCharactersUseCase = @JvmSuppressWildcards UseCaseWithParam<SearchQueryRequest, @JvmSuppressWildcards List<Character>>

data class SearchQueryRequest(
    val query: String
)