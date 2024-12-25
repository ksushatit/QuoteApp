package com.example.viewmodels

import androidx.lifecycle.ViewModel
import com.example.Env
import com.example.api.requests.QuotesRequest
import com.example.model.Quote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val env: Env) : ViewModel() {
    val message = "HOME"

    private val quotesState = MutableStateFlow(listOf<Quote>())
    val quotes = quotesState.asStateFlow()

    suspend fun onSearch(query: String) {
        val res =
            env.apiClient.fetchQuotes(QuotesRequest(query.takeIf { it.isNotEmpty() })).quotes

        quotesState.value = res.filter { !isBad(it) }.map(this::sanitizeQuote)
    }

    suspend fun onQotd() {
        val res = env.apiClient.fetchQotd().quote

        if (isBad(res)) {
            onQotd()
            return
        }
    
        quotesState.value = listOf(sanitizeQuote(res))
    }

    private fun sanitizeQuote(quote: Quote) =
        quote.copy(body = quote.body.replace("<br>", "\n"))

    private fun isBad(quote: Quote) = quote.body.startsWith("body") || quote.body.startsWith("test")
}