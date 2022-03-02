package com.onats.common_ui.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<T: ScreenState>(
    initialState: T
) : ViewModel() {

    private val _screenState: MutableStateFlow<T> = MutableStateFlow(initialState)
    val screenState: MutableStateFlow<T> = _screenState

    val currentScreenStateValue
        get() = _screenState.value

    fun setScreenState(state: T) {
        _screenState.value = state
    }
}