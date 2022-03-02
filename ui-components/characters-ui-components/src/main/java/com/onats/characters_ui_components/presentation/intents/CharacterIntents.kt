package com.onats.characters_ui_components.presentation.intents

import com.onats.common_ui.presentation.MVIIntent

object LoadCharacters: MVIIntent

data class QueryCharacters(val query: String): MVIIntent