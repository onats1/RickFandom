package com.onats.characters.ui.intents

import com.onats.common_ui.presentation.MVIIntent

object LoadCharacters: MVIIntent

data class QueryCharacters(val query: String): MVIIntent