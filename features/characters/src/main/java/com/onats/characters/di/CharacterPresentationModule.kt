package com.onats.characters.di

import com.onats.characters.presentation.CharacterScreenStateMachine
import com.onats.characters.presentation.intentprocessors.CharacterIntentProcessor
import com.onats.core_character.usecases.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterPresentationModule {

    @Provides
    @Singleton
    fun provideCharacterIntentProcessor(getAllCharactersUseCase: GetAllCharactersUseCase): CharacterIntentProcessor {
        return CharacterIntentProcessor(getAllCharactersUseCase)
    }

    @Provides
    @Singleton
    fun provideCharacterStateMachine(): CharacterScreenStateMachine = CharacterScreenStateMachine()
}