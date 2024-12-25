package com.example.api

import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiClientTest {
    @Test
    fun fetchQotd(): Unit = runBlocking {
        val client = ApiClient()
        println(client.fetchQotd())
    }

    @Test
    fun fetchQuotes() {
    }
}