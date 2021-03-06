package com.onats.rickfandom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.onats.common_ui.theme.RickFandomTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @[ExperimentalComposeUiApi ExperimentalMaterialApi ExperimentalFoundationApi]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickFandomTheme {
                val navController = rememberNavController()
                HomeScreen(navController = navController)
            }
        }
    }
}

@[Composable ExperimentalComposeUiApi ExperimentalMaterialApi ExperimentalFoundationApi]
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Screens(navController = navController)
    }
}


@Preview(showBackground = true)
@[Composable ExperimentalComposeUiApi ExperimentalMaterialApi ExperimentalFoundationApi]
fun DefaultPreview() {
    HomeScreen()
}