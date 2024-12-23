package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavRoute<T>(val name: String, val route: T, val icon: @Composable () -> Unit) {
    companion object {
        val routes = listOf(
            NavRoute("Home", 0) { Icon(Icons.Default.Home, "Home") }
        )
    }
}

@Composable
fun Nav(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        for (route in NavRoute.routes) {
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == route.name } == true,
                label = { Text(route.name) },
                icon = route.icon,
                onClick =  {
                    navController.popBackStack(route.name, inclusive = true, saveState = true)
                    navController.navigate(route.name)
                },
            )
        }
    }
}