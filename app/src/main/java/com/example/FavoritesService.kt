package com.example

import com.example.data.FavoriteQuoteDao
import com.example.model.Quote

class FavoritesService(private val dao: FavoriteQuoteDao) {
    private val favoriteIds = dao.getAllIds().toMutableSet()

    fun isFavorite(quote: Quote) = favoriteIds.contains(quote.id)

    fun addFavorite(quote: Quote) {
        dao.insertQuote(quote)
        favoriteIds.add(quote.id)
    }

    fun removeFavorite(quote: Quote) {
        dao.deleteQuoteById(quote.id)
        favoriteIds.remove(quote.id)
    }

    fun getQuotes(): List<Quote> = dao.getAll()
}