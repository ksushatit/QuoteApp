package com.example.api

import com.example.api.requests.QuotesRequest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiClientTest {
    @Test
    fun fetchQotd() = runBlocking {
        val client = ApiClient()
        println(client.fetchQotd())
    }

    @Test
    fun fetchQuotes() = runBlocking {
        val client = ApiClient()
        println(client.fetchQuotes(QuotesRequest(filter = "What")))
    }
}