package com.onats.rickfandom.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.onats.characters.presentation.ui.screens.CharactersListScreen
import com.onats.episodes.presentation.ui.screens.EpisodesListScreen
import com.onats.location.presentation.ui.screens.LocationsListScreen

sealed class BottomNavigationScreens(val route: String){
    object CharacterScreen: BottomNavigationScreens("Characters")
    object EpisodesScreen: BottomNavigationScreens("Episodes")
    object LocationsScreen: BottomNavigationScreens("Locations")
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.CharacterScreen,
        BottomNavigationScreens.EpisodesScreen,
        BottomNavigationScreens.LocationsScreen
    )

    Scaffold(
        bottomBar = {
            BottomNavigationView(
                navController = navController,
                items = bottomNavigationItems
            )
        }
    ) {
        MainScreenNavigationConfigurations(navController = navController)
    }


}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavigationScreens.CharacterScreen.route) {
        composable(BottomNavigationScreens.CharacterScreen.route) {
            CharactersListScreen()
        }
        composable(BottomNavigationScreens.EpisodesScreen.route) {
            EpisodesListScreen()
        }
        composable(BottomNavigationScreens.LocationsScreen.route) {
            LocationsListScreen()
        }
    }
}

@Composable
private fun BottomNavigationView(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {

    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icons.Default.Call },
                label = { Text("Rick") },
                selected = currentRoute == screen.route,
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }

}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}














