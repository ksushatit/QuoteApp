package com.example.viewmodels

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class ViewModelFactory<VM : ViewModel>(val construct: () -> VM) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = construct() as T
}

@Composable
inline fun <reified VM : ViewModel> getViewModel(noinline construct: () -> VM): VM =
    viewModel(factory = ViewModelFactory(construct))