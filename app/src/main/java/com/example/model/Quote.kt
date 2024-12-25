package com.example.model

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
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
    @JsonProperty("private")
    val privat: Boolean,
    val body: String
)
