package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite_quote")
data class FavoriteQuote(
    @PrimaryKey val id: Int,
    val url: String,
    val author: String,
    val body: String
)
