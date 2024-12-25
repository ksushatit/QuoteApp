package com.example.api.responses

import com.example.model.Quote
import kotlinx.serialization.Serializable

@Serializable
data class QuotesResponse(
    val page: Int,
    val lastPage: Boolean,
    val quotes: List<Quote> = listOf()
)