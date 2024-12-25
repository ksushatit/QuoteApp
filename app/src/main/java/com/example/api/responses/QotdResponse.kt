package com.example.api.responses

import com.example.model.Quote
import kotlinx.serialization.Serializable

@Serializable
data class QotdResponse(
    val qotdDate: String,
    val quote: Quote
)