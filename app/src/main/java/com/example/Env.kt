package com.example

import androidx.navigation.NavHostController
import com.example.api.ApiClient
import com.example.data.AppDatabase

data class Env(
    val navController: NavHostController,
    val apiClient: ApiClient,
    val database: AppDatabase
)
