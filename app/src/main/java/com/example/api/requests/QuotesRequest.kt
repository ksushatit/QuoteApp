package com.example.api.requests

import io.ktor.resources.Resource

enum class QuotesRequestType(val type: String) {
    AUTHOR("author"),
    TAG("tag"),
    USER("user")
}

@Resource("/quotes")
class QuotesRequest(
    filter: String? = null,
    type: String? = null,
    private: Boolean? = null,
    hidden: Boolean? = null,
    page: Int? = null
)