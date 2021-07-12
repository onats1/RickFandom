package com.onats.common_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onats.common_ui.theme.RickFandomTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip

@Composable
fun AppBarInfo(
    title: String,
) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth()
    ) {
        var text by remember { mutableStateOf("") } // Will be updated accordingly
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
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
                    textColor = Color.Black,
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
        AppBarInfo(title = "Title")
    }
}

