package com.example.api

import com.example.api.requests.QotdRequest
import com.example.api.requests.QuotesRequest
import com.example.api.responses.QotdResponse
import com.example.api.responses.QuotesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.get
import io.ktor.serialization.kotlinx.json.*

class ApiClient {
    val client = HttpClient {
        install(Resources)
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            host = "https://favqs.com/api"
        }
    }

    suspend fun fetchQotd(): QotdResponse = client.get(QotdRequest()).body()

    suspend fun fetchQuotes(req: QuotesRequest = QuotesRequest()): QuotesResponse =
        client.get(req).body()
}