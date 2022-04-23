package com.onats.characters.ui

import androidx.lifecycle.viewModelScope
import com.onats.characters_ui_components.presentation.CharacterViewModel
import com.onats.characters_ui_components.presentation.intents.CharacterIntentProcessor
import com.onats.characters_ui_components.presentation.intents.LoadCharacters
import com.onats.common_ui.presentation.MVIIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModelImpl
@Inject
constructor(
    private val characterIntentProcessor: CharacterIntentProcessor
) : CharacterViewModel() {

    init {
        processIntent(LoadCharacters)
    }

    override fun processIntent(intent: MVIIntent) {
        viewModelScope.launch {
            characterIntentProcessor.processIntent(intent = intent, currentScreenState = currentScreenStateValue) { intentResult ->
                setScreenState(intentResult)
            }
        }
    }
}