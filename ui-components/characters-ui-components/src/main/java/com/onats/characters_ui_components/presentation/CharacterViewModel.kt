package com.onats.characters_ui_components.presentation

import com.onats.characters_ui_components.presentation.charactercomponentstates.CharacterScreenStates
import com.onats.common_ui.presentation.BaseViewModel
import com.onats.common_ui.presentation.MVIIntent

abstract class CharacterViewModel: BaseViewModel<CharacterScreenStates>(
    CharacterScreenStates.InitialState) {

    abstract fun processIntent(intent: MVIIntent)
}