package com.onats.characters_ui_components.components.characterqueryfieldcomponent.characterqueryfieldstates

sealed class CharacterQueryFieldComponentStates(val componentData: CharacterQueryFieldData = CharacterQueryFieldData()) {
    object InitialState: CharacterQueryFieldComponentStates()
    data class QueryInProgress(private val data: CharacterQueryFieldData): CharacterQueryFieldComponentStates(data)
}


data class CharacterQueryFieldData(
    val query: String = ""
)

fun CharacterQueryFieldComponentStates.reduceToQueryInProgressState(query: String): CharacterQueryFieldData {
    return componentData.copy(
        query = query
    )
}