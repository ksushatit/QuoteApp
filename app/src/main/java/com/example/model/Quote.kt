package com.example.model

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val id: Int,
    val favoritesCount: Int = 0,
    val dialogue: Boolean = false,
    val favorite: Boolean = false,
    val tags: List<String> = listOf(),
    val url: String = "",
    val upvotesCount: Int = 0,
    val downvotesCount: Int = 0,
    val author: String = "",
    val authorPermalink: String = "",
    @JsonProperty("private")
    val privat: Boolean = false,
    val source: String? = null,
    val context: String? = null,
    val body: String
)
