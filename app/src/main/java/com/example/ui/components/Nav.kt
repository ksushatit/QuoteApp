package com.example.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.Env
import com.example.ui.screens.FavoriteScreen
import com.example.ui.screens.HomeScreen

@Composable
fun Env.BottomNavigationBar() {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "home",
            icon = { Icon(Icons.Default.Home, "Home") },
            label = { Text("Home") },
            onClick = {
                navController.navigate("home") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
        )
        NavigationBarItem(
            selected = currentRoute == "favorite",
            icon = { Icon(Icons.Default.ThumbUp, "Favorite") },
            label = { Text("Favorites") },
            onClick = {
                navController.navigate("favorite") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
        )
    }
}

@Composable
fun Env.NavigationHost(
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("home") { HomeScreen() }
        composable("favorite") { FavoriteScreen() }
    }
}