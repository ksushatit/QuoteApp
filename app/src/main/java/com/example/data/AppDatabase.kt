package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.model.Quote

@Database(entities = [Quote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteQuoteDao(): FavoriteQuoteDao
}