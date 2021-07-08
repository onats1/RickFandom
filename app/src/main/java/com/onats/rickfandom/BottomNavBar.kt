package com.onats.rickfandom

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import co.onats.home.ui.HomeScreen
import com.onats.characters.ui.CharactersScreen
import com.onats.episodes.ui.EpisodesScreen
import com.onats.location.ui.LocationScreen

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.home_icon, "Home")
    object Characters : NavigationItem("music", R.drawable.characters_icon, "Characters")
    object Episodes : NavigationItem("movies", R.drawable.episodes_icon, "Episodes")
    object Location : NavigationItem("books", R.drawable.location_icon, "Location")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Episodes,
        NavigationItem.Characters,
        NavigationItem.Location
    )

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.black),
        contentColor = colorResource(R.color.purple_200)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = colorResource(id = R.color.white),
                unselectedContentColor = colorResource(id = R.color.white),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Screens(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Characters.route) {
            CharactersScreen()
        }
        composable(NavigationItem.Episodes.route) {
            EpisodesScreen()
        }
        composable(NavigationItem.Location.route) {
            LocationScreen()
        }
    }
}