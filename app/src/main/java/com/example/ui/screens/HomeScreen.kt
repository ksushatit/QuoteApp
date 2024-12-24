package com.example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.Env
import com.example.viewmodels.HomeViewModel
import com.example.viewmodels.getViewModel

@Composable
fun Env.HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = getViewModel { HomeViewModel(this) }

    Column(modifier.fillMaxSize()) {
        Text(viewModel.message)
        Text("balls")
    }
}
