package com.example.api

import com.example.api.requests.QotdRequest
import com.example.api.requests.QuotesRequest
import com.example.api.responses.QotdResponse
import com.example.api.responses.QuotesResponse
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.header
import io.ktor.serialization.jackson.jackson

class ApiClient {
    private val client = HttpClient {
        install(Resources)
        install(ContentNegotiation) {
            jackson {
                propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
            }
        }
        defaultRequest {
            url("https://favqs.com/api/")
            // Это НЕ УГРОЗА БЕЗОПАСНОСТИ, токен не служит аутентификацией и его можно сгенерировать
            // без аккаунта
            header("Authorization", "Token token=f68a3e7c58b592e252ceb18ee51ab205")
        }
    }

    suspend fun fetchQotd(): QotdResponse = client.get(QotdRequest()).body()

    suspend fun fetchQuotes(req: QuotesRequest = QuotesRequest()): QuotesResponse =
        client.get(req).body()
}