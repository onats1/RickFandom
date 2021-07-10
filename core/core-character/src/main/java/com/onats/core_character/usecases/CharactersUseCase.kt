package com.onats.core_character.usecases

import com.onats.common.UseCase
import com.onats.core_character.models.Character

typealias GetAllCharactersUseCase = @JvmSuppressWildcards UseCase<List<Character>>
