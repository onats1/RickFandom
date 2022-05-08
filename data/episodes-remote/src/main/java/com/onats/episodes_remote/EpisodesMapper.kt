package com.onats.episodes_remote

import com.onats.core_episodes.EpisodesQuery
import com.onats.core_episodes.data.CharacterMapper
import com.onats.core_episodes.data.EpisodesMapper
import com.onats.core_episodes.models.Episode
import com.onats.core_episodes.models.EpisodeCharacter
import javax.inject.Inject

class EpisodesMapperImpl
@Inject
constructor(
    private val characterMapper: CharacterMapper
): EpisodesMapper {
    override fun mapToDto(domain: Episode): EpisodesQuery.Episode {
        return EpisodesQuery.Episode("", "", "", "", listOf())
    }

    override fun mapToDomain(model: EpisodesQuery.Episode): Episode {
        return Episode(
            name = model.name?:"",
            airDate = model.air_date?:"",
            episode = model.episode?:"",
            characters = characterMapper.mapToDomainList(model.characters.filterNotNull())
        )
    }

}

class CharactersMapperImpl
@Inject
constructor(): CharacterMapper {
    override fun mapToDto(domain: EpisodeCharacter): EpisodesQuery.Character {
        return EpisodesQuery.Character("", "", "")
    }

    override fun mapToDomain(model: EpisodesQuery.Character): EpisodeCharacter {
        return EpisodeCharacter(
            name = model.name?:"",
            image = model.image?:"",
            species = model.species?:""
        )
    }
}