package com.onats.characters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onats.characters.presentation.CharacterResult
import com.onats.characters.presentation.CharacterScreenStateMachine
import com.onats.characters.presentation.CharacterScreenStates
import com.onats.characters.presentation.intentprocessors.CharacterIntentProcessor
import com.onats.characters.presentation.intentprocessors.CharactersIntent
import com.onats.characters.presentation.intentprocessors.ViewIntents
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
    private val intentProcessor: CharacterIntentProcessor,
    private val stateMachine: CharacterScreenStateMachine
) : ViewModel() {

    private val _charactersState = MutableStateFlow<CharacterScreenStates>(CharacterScreenStates())
    val characterState: StateFlow<CharacterScreenStates> = _charactersState

    init {
        dispatchIntent(
            CharactersIntent.GetAllCharactersIntent
        )
    }

    fun dispatchIntent(intent: ViewIntents) {
        viewModelScope.launch {
            intentProcessor.executeIntent(intent).collect { result ->
                result as CharacterResult
                with(stateMachine) {
                    _charactersState.value = _charactersState.value reduceTo result
                }
            }
        }
    }

}