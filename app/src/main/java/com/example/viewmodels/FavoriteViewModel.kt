package com.example.viewmodels

import androidx.lifecycle.ViewModel
import com.example.Env

class FavoriteViewModel(private val env: Env) : ViewModel() {
    val message = "FAVORITES"
}