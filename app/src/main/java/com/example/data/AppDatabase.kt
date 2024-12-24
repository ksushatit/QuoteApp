package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteQuote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteQuoteDao(): FavoriteQuoteDao
}