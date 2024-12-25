package com.example

import androidx.navigation.NavHostController
import com.example.api.ApiClient

data class Env(
    val navController: NavHostController,
    val apiClient: ApiClient,
    val favoritesService: FavoritesService
)
