package com.onats.characters_ui_components.presentation.intents

import com.onats.common_ui.presentation.MVIIntent

object LoadCharacters: MVIIntent

data class QueryInProgress(val query: String): MVIIntent

data class ExecuteQuery(val query: String): MVIIntent