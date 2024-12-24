package com.example.viewmodels

import androidx.lifecycle.ViewModel
import com.example.Env
import com.example.api.responses.QuotesResponse

class HomeViewModel(private val env: Env) : ViewModel() {
    val message = "HOME"

    suspend fun search(query: String): QuotesResponse {
        // TODO implement
        throw NotImplementedError()
    }
}