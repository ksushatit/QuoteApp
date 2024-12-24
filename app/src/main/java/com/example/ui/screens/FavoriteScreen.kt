package com.example.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.Env
import com.example.viewmodels.FavoriteViewModel
import com.example.viewmodels.getViewModel

@Composable
fun Env.FavoriteScreen() {
    val viewModel = getViewModel { FavoriteViewModel(this) }
    Text(viewModel.message)
}