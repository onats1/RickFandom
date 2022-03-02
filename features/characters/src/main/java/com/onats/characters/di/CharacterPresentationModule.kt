package com.onats.characters.di

import com.onats.characters_ui_components.presentation.intents.CharacterIntentProcessor
import com.onats.core_character.usecases.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object CharacterPresentationModule {

    @[Singleton Provides]
    fun provideCharacterIntentProcessor(
        getAllCharactersUseCase: GetAllCharactersUseCase
    ): CharacterIntentProcessor {
        return CharacterIntentProcessor(getAllCharactersUseCase)
    }
}