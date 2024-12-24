package com.example.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.Env
import com.example.viewmodels.HomeViewModel
import com.example.viewmodels.getViewModel

@Composable
fun Env.HomeScreen() {
    val viewModel = getViewModel { HomeViewModel(this) }
    Text(viewModel.message)
}
