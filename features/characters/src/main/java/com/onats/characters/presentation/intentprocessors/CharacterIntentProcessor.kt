package com.onats.characters.presentation.intentprocessors

import com.onats.characters.presentation.CharacterResult
import com.onats.characters.state.mvibase.MviResult
import com.onats.common.DomainResult
import com.onats.core_character.usecases.GetAllCharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterIntentProcessor
@Inject
constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : MviIntentProcessor {
    override fun executeIntent(intent: ViewIntents): Flow<MviResult> {
        return when (intent) {
            CharactersIntent.GetAllCharactersIntent -> getAllCharacters()
            is CharactersIntent.CharacterSearchIntent -> searchCharacters(query = intent.query)
            else -> throw IllegalArgumentException()
        }
    }

    private fun getAllCharacters(): Flow<CharacterResult.CharacterDisplayResult> = flow {
        getAllCharactersUseCase().collect { domainResult ->
            if (domainResult is DomainResult.Success) {
                emit(
                    CharacterResult.CharacterDisplayResult.CharactersRetrieved(characters = domainResult.data.map { it.summary })
                )
            }
        }
    }

    private fun searchCharacters(query: String): Flow<CharacterResult.CharacterSearchResult> = flow {

    }
}