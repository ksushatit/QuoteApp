package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Env
import com.example.viewmodels.HomeViewModel
import com.example.viewmodels.getViewModel
import kotlinx.coroutines.launch

@Composable
fun Env.HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = getViewModel { HomeViewModel(this) }

    var query by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val runSearch: () -> Unit = {
        coroutineScope.launch {
            viewModel.onSearch(query)
        }
    }

    val runQotd: () -> Unit = {
        coroutineScope.launch {
            viewModel.onQotd()
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("QuoteApp", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TextField(
            value = query,
            modifier = modifier.fillMaxWidth(),
            label = { Text("Find a quote") },
            onValueChange = {
                query = it
            })
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(onClick = runSearch) {
                Text("Search")
            }
            Button(onClick = runQotd) {
                Text("Random quote")
            }
        }

        val quotes by viewModel.quotes.collectAsState()

        LazyColumn {
            items(quotes) { quote ->
                Card(modifier = modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Text(quote.body, Modifier.padding(bottom = 8.dp))
                        Text("© ${quote.author}")
                    }
                }
            }
        }
    }
}
