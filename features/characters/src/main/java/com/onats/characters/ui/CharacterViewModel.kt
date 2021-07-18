package com.onats.characters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onats.common.DomainResult
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

    private val _charactersState = MutableStateFlow<List<CharacterSummary>>(listOf())
    val characterState: StateFlow<List<CharacterSummary>> = _charactersState

    init {
        viewModelScope.launch {
            getAllCharactersUseCase().collect { domainResult ->
                if (domainResult is DomainResult.Success) {
                    val characterSummaryList = domainResult.data.map { it -> it.summary }
                    _charactersState.value = characterSummaryList
                }
            }
        }
    }

}