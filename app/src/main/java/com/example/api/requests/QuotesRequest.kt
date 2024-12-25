package com.example.api.requests

import io.ktor.resources.Resource

enum class QuotesRequestType(val type: String) {
    AUTHOR("author"),
    TAG("tag"),
    USER("user")
}

@Resource("/quotes")
class QuotesRequest(
    val filter: String? = null,
    val type: String? = null,
    val private: Boolean? = null,
    val hidden: Boolean? = null,
    val page: Int? = null
)