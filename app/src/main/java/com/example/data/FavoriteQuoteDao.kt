package com.example.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FavoriteQuoteDao {
    @Query("SELECT * FROM favorite_quote")
    fun getAll(): List<FavoriteQuote>
}