package com.onats.characters.ui

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.onats.characters_ui_components.presentation.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterMainActivity: ComponentActivity() {

    val characterViewModel by viewModels<CharacterViewModel>()
}