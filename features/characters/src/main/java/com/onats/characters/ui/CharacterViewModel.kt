package com.onats.characters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onats.characters.ui.characterstates.CharacterDisplayStates
import com.onats.common.DomainResult
import com.onats.core_character.models.Character
import com.onats.core_character.models.CharacterSummary
import com.onats.core_character.usecases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel
@Inject
constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _charactersState = MutableStateFlow<CharacterDisplayStates>(CharacterDisplayStates.InitialState)
    val characterState: StateFlow<CharacterDisplayStates> = _charactersState

    init {
        viewModelScope.launch {
            if (_charactersState.value !is CharacterDisplayStates.CharactersLoaded) {
                _charactersState.value = CharacterDisplayStates.LoadingState
                getAllCharactersUseCase().collect { domainResult ->
                    if (domainResult is DomainResult.Success) {
                        val characterSummaryList = domainResult.data.map { it.summary }
                        _charactersState.value = CharacterDisplayStates.CharactersLoaded(
                            characters = characterSummaryList
                        )
                    }
                }
            }

        }
    }

}