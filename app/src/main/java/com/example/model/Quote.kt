package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@Serializable
@Entity("favorite_quote")
@JsonIgnoreProperties(
    "favorites_count",
    "dialogue",
    "favorite",
    "tags",
    "url",
    "upvotes_count",
    "downvotes_count",
    "author_permalink",
    "private",
    "source",
    "context"
)
data class Quote(
    @PrimaryKey
    val id: Int = 0,
    val author: String = "",
    val body: String = ""
) {
    fun isBad(): Boolean = body.startsWith("body")
            || body.startsWith("test")
            || body == author
            || body.length < 10
            || author.isEmpty()

    fun sanitized(): Quote = copy(body = body.replace("<br>", "\n"))
}