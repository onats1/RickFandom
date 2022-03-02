package com.onats.common_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onats.common_ui.theme.RickFandomTheme

@Composable
fun AppBarInfo(
    title: String,
    searchValue: String,
    onSearchValueChange: (String) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = searchValue,
                onValueChange = onSearchValueChange,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .wrapContentHeight()
                ,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    leadingIconColor = MaterialTheme.colors.secondary,
                    focusedBorderColor = MaterialTheme.colors.primaryVariant,
                    unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
                    textColor = Color.White,
                    cursorColor = Color.Black
                ),
                shape = MaterialTheme.shapes.medium
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppBar() {
    RickFandomTheme {
        AppBarInfo(title = "Title", searchValue = "") {

        }
    }
}

