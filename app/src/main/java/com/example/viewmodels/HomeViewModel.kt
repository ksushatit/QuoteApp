package com.example.viewmodels

import androidx.lifecycle.ViewModel
import com.example.Env
import com.example.model.Quote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val env: Env) : ViewModel() {
    val message = "HOME"

    private val quotesState = MutableStateFlow(listOf<Quote>())
    val quotes = quotesState.asStateFlow()

    suspend fun onSearch(query: String) {
        // TODO implement
    }

    suspend fun onQotd() {
        val res = env.apiClient.fetchQotd()
        quotesState.value = listOf(res.quote)
    }
}