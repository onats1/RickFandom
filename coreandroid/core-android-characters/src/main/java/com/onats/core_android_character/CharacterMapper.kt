package com.onats.core_android_character

import com.onats.common.Mapper
import com.onats.core_character.models.Character
import com.onats.core_character.models.Location
import com.onats.core_character.models.Origin

typealias CharacterMapper = @JvmSuppressWildcards Mapper<CharacterDto, Character>

typealias LocationMapper = @JvmSuppressWildcards Mapper<LocationDto, Location>

typealias OriginMapper = @JvmSuppressWildcards Mapper<OriginDto, Origin>