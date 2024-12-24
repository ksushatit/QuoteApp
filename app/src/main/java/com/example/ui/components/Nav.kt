package com.example.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.Env
import com.example.ui.screens.FavoriteScreen
import com.example.ui.screens.HomeScreen

data class NavRoute(
    val name: String,
    val icon: @Composable () -> Unit,
    val screen: @Composable () -> Unit,
) {
    companion object {
        val routes = listOf(
            NavRoute(
                "home",
                icon = { Icon(Icons.Default.Home, "Home") },
                screen = { HomeScreen() }
            ),
            NavRoute(
                "fav",
                icon = { Icon(Icons.Default.Star, "Favorite") },
                screen = { FavoriteScreen() }
            )
        )
    }
}

@Composable
fun Env.BottomNavigationBar() {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavRoute.routes.forEach { route ->
            NavigationBarItem(
                selected = currentRoute == route.name,
                icon = route.icon,
                onClick = {
                    navController.navigate(route.name) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Composable
fun Env.NavigationHost(paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues)
    ) {
        NavRoute.routes.forEach { route ->
            composable(route.name) { route.screen() }
        }
    }
}