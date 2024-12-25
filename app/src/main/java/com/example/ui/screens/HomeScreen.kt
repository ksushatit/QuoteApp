package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
            val response = viewModel.search(query)
            // TODO do sth with the response
        }
    }

    val runQotd: () -> Unit = {

    }

    Column(
        modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("QuoteApp", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TextField(value = query, label = { Text("Поиск цитаты") }, onValueChange = {
            query = it
        })
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = runSearch) {
                Text("Найти цитату")
            }
            Button(onClick = runQotd) {
                Text("Цитата дня")
            }
        }
    }
}
