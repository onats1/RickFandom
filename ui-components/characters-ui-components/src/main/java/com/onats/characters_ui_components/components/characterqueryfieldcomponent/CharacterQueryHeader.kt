package com.onats.characters_ui_components.components.characterqueryfieldcomponent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.onats.characters_ui_components.components.characterqueryfieldcomponent.characterqueryfieldstates.CharacterQueryFieldComponentStates
import com.onats.common_ui.components.AppBarInfo

@Composable
fun CharacterQueryHeader(
    modifier: Modifier = Modifier.testTag("query_header"),
    queryFieldComponentStates: CharacterQueryFieldComponentStates = CharacterQueryFieldComponentStates.InitialState,
    onQueryValueChanged: (String) -> Unit,
    executeQuery: (String) -> Unit
) {
    AppBarInfo(
        modifier = modifier,
        searchValue = queryFieldComponentStates.componentData.query,
        onSearchValueChange = onQueryValueChanged,
        executeQuery = { executeQuery(queryFieldComponentStates.componentData.query) }
    )
}