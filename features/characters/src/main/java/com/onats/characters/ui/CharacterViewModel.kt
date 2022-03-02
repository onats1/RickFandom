package com.onats.characters.ui

import androidx.lifecycle.viewModelScope
import com.onats.characters_ui_components.presentation.CharacterViewModel
import com.onats.characters_ui_components.presentation.characterstates.CharacterDisplayScreenStates
import com.onats.characters_ui_components.presentation.intents.CharacterIntentProcessor
import com.onats.characters_ui_components.presentation.intents.LoadCharacters
import com.onats.common_ui.presentation.BaseViewModel
import com.onats.common_ui.presentation.MVIIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterViewModelImpl
@Inject
constructor(
    private val characterIntentProcessor: CharacterIntentProcessor
) : CharacterViewModel() {

    init {
        processIntent(LoadCharacters)
        Timber.e("intent called")
    }

    override fun processIntent(intent: MVIIntent) {
        viewModelScope.launch {
            characterIntentProcessor.processIntent(intent = intent, currentScreenState = currentScreenStateValue) { intentResult ->
                Timber.e("result emitted")
                setScreenState(intentResult)
            }
        }
    }
}