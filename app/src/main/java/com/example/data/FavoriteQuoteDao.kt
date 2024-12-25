package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.Quote

@Dao
interface FavoriteQuoteDao {
    @Query("SELECT * FROM favorite_quote")
    fun getAll(): List<Quote>

    @Query("SELECT id FROM favorite_quote")
    fun getAllIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuote(quote: Quote)

    @Query("DELETE FROM favorite_quote WHERE id=:id")
    fun deleteQuoteById(id: Int)
}