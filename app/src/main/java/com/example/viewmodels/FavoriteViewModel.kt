package com.example.viewmodels

import androidx.lifecycle.ViewModel
import com.example.Env
import com.example.model.Quote
import kotlinx.coroutines.flow.MutableStateFlow

class FavoriteViewModel(private val env: Env) : ViewModel() {
    val quotes = MutableStateFlow(env.favoritesService.getQuotes())

    fun onRemoveQuote(quote: Quote) {
        env.favoritesService.removeFavorite(quote)
        quotes.value = quotes.value.filterNot { it.id == quote.id }
    }
}