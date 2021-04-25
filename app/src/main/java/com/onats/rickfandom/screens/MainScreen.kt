package com.onats.rickfandom.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.onats.characters.framework.presentation.ui.screens.CharactersListScreen
import com.onats.episodes.presentation.ui.screens.EpisodesListScreen
import com.onats.location.presentation.ui.screens.LocationsListScreen
import com.onats.rickfandom.R

sealed class BottomNavigationScreens(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {
    object CharacterScreen :
        BottomNavigationScreens("Characters", R.drawable.ic_baseline_person_24, R.string.characters)

    object EpisodesScreen :
        BottomNavigationScreens("Episodes", R.drawable.ic_baseline_list_24, R.string.episodes)

    object LocationsScreen : BottomNavigationScreens(
        "Locations",
        R.drawable.ic_baseline_location_on_24,
        R.string.locations
    )
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
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = ""
                    )
                },
                label = { Text(stringResource(id = screen.title)) },
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














