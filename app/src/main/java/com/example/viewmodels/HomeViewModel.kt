package com.example.viewmodels

import androidx.lifecycle.ViewModel
import com.example.Env

class HomeViewModel(private val env: Env) : ViewModel() {
    val message = "HOME"
}