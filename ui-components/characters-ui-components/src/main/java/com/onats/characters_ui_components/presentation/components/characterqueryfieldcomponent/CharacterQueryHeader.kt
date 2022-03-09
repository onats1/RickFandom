package com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.onats.characters_ui_components.R
import com.onats.characters_ui_components.presentation.components.characterqueryfieldcomponent.characterqueryfieldstates.CharacterQueryFieldComponentStates
import com.onats.common_ui.components.AppBarInfo

@Composable
fun CharacterQueryHeader(
    queryFieldComponentStates: CharacterQueryFieldComponentStates,
    onQueryValueChanged: (String) -> Unit
) {
    AppBarInfo(
        title = stringResource(id = R.string.character),
        searchValue = queryFieldComponentStates.componentData.query,
    ) { query ->
        onQueryValueChanged(query)
    }
}