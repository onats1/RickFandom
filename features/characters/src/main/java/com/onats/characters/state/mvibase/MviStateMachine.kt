package com.onats.characters.state.mvibase

interface MviStateMachine<State: MviState, Result: MviResult> {

    infix fun State.reduceTo(result: Result): State
}