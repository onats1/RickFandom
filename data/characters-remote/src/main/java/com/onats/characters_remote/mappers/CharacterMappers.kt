package com.onats.characters_remote.mappers

import com.onats.core_android_character.*
import com.onats.core_character.models.Character
import com.onats.core_character.models.Location
import com.onats.core_character.models.Origin
import javax.inject.Inject

class OriginMapperImpl : OriginMapper {
    override fun mapToDto(domain: Origin): OriginDto {
        return OriginDto(
            name = domain.name,
            url = domain.url
        )
    }

    override fun mapToDomain(model: OriginDto): Origin {
        return if (model.name.isNullOrEmpty() || model.url.isNullOrEmpty()) {
            Origin.NO_ORIGIN
        } else {
            Origin(
                name = model.name!!,
                url = model.url!!
            )
        }
    }
}

class LocationMapperImpl : LocationMapper {
    override fun mapToDto(domain: Location): LocationDto {
        return LocationDto(
            name = domain.name,
            url = domain.url
        )
    }

    override fun mapToDomain(model: LocationDto): Location {
        return if (model.name.isNullOrEmpty() || model.url.isNullOrEmpty()) {
            Location.NO_LOCATION
        } else {
            Location(
                name = model.name!!,
                url = model.url!!
            )
        }
    }
}

class CharacterMapperImpl
@Inject
constructor(
    private val originMapper: OriginMapper,
    private val locationMapper: LocationMapper
) : CharacterMapper {
    override fun mapToDto(domain: Character): CharacterDto {
        return CharacterDto(
            id = domain.id,
            name = domain.name,
            episode = domain.episodes,
            image = domain.image,
            url = domain.url,
            created = domain.created,
            gender = domain.gender,
            location = locationMapper.mapToDto(domain.location),
            origin = originMapper.mapToDto(domain.origin),
            species = domain.species,
            status = domain.status
        )
    }

    override fun mapToDomain(model: CharacterDto): Character {
        return Character(
            id = model.id ?: -1,
            name = model.name ?: "",
            episodes = model.episode ?: listOf(),
            status = model.status ?: "",
            species = model.species ?: "",
            origin = originMapper.mapToDomain(model.origin!!),
            location = locationMapper.mapToDomain(model.location!!),
            gender = model.gender ?: "",
            created = model.created ?: "",
            url = model.url ?: "",
            image = model.image ?: ""
        )
    }

}



















