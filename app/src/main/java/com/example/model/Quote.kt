package com.example.model

data class Quote(
    val id: Int,
    val favoritesCount: Int,
    val dialogue: Boolean,
    val favorite: Boolean,
    val tags: List<String>,
    val url: String,
    val upvotesCount: Int,
    val downvotesCount: Int,
    val author: String,
    val authorPermalink: String,
    val body: String
)
