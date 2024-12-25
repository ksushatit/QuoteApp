package com.example.viewmodels

import androidx.lifecycle.ViewModel
import com.example.Env
import com.example.api.requests.QuotesRequest
import com.example.model.Quote
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(private val env: Env) : ViewModel() {
    val quotes = MutableStateFlow(listOf<Quote>())

    val page = MutableStateFlow(1)
    val lastPage = MutableStateFlow(true)

    private var lastQuery: String = ""

    suspend fun onSearch(query: String, pageNum: Int? = null) {
        if (query.isEmpty()) return

        val req = QuotesRequest(
            filter = query.takeIf { it.isNotEmpty() },
            page = pageNum
        )
        val res = env.apiClient.fetchQuotes(req)
        
        if (res.quotes.firstOrNull()?.id == 0) {
            quotes.value = listOf()
            page.value = 1
            lastPage.value = true
            return
        }

        quotes.value = res.quotes.filter { !it.isBad() }.map { it.sanitized() }

        lastQuery = query
        page.value = res.page
        lastPage.value = res.lastPage
    }

    suspend fun onNextPage() {
        if (lastQuery.isEmpty() || lastPage.value) throw IllegalStateException()
        onSearch(lastQuery, page.value + 1)
    }

    suspend fun onPreviousPage() {
        if (lastQuery.isEmpty() || page.value <= 1) throw IllegalStateException()
        onSearch(lastQuery, page.value - 1)
    }

    suspend fun onQotd() {
        val res = env.apiClient.fetchQotd().quote

        if (res.isBad()) {
            onQotd()
            return
        }

        quotes.value = listOf(res.sanitized())

        lastQuery = ""
        page.value = 1
        lastPage.value = true
    }

    fun isFavorite(quote: Quote) = env.favoritesService.isFavorite(quote)

    fun addFavorite(quote: Quote) = env.favoritesService.addFavorite(quote)

    fun removeFavorite(quote: Quote) = env.favoritesService.removeFavorite(quote)
}