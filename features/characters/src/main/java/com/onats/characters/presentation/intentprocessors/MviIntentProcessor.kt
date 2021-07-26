package com.onats.characters.presentation.intentprocessors

import com.onats.characters.state.mvibase.MviResult
import kotlinx.coroutines.flow.Flow

interface MviIntentProcessor {

    fun executeIntent(intent: ViewIntents): Flow<MviResult>
}