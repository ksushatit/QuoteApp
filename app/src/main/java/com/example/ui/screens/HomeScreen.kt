package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
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
import com.example.ui.components.Quote
import com.example.viewmodels.HomeViewModel
import com.example.viewmodels.getViewModel
import kotlinx.coroutines.launch

@Composable
fun Env.HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = getViewModel { HomeViewModel(this) }

    var query by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val listState = rememberLazyListState()

    fun runSearch() {
        coroutineScope.launch {
            viewModel.onSearch(query)
        }
    }

    fun runQotd() {
        coroutineScope.launch {
            viewModel.onQotd()
        }
    }

    fun runPreviousPage() {
        coroutineScope.launch {
            viewModel.onPreviousPage()
            listState.scrollToItem(0)
        }
    }

    fun runNextPage() {
        coroutineScope.launch {
            viewModel.onNextPage()
            listState.scrollToItem(0)
        }
    }

    val quotes by viewModel.quotes.collectAsState()
    val page by viewModel.page.collectAsState()
    val lastPage by viewModel.lastPage.collectAsState()

    Box(
        modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
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
                Button(onClick = ::runSearch) {
                    Text("Search")
                }
                Button(onClick = ::runQotd) {
                    Text("Random quote")
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), state = listState) {
                items(quotes) { quote ->
                    Quote(modifier.fillMaxWidth(), quote)
                }
            }
        }

        if (!lastPage || page > 1)
            PageNavigation(
                Modifier.align(Alignment.BottomCenter),
                page,
                lastPage,
                ::runPreviousPage,
                ::runNextPage
            )
    }
}

@Composable
fun PageNavigation(
    modifier: Modifier,
    page: Int,
    lastPage: Boolean,
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit
) = Row(
    modifier,
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    val mod = Modifier
        .width(36.dp)
        .height(36.dp)

    Box(mod, contentAlignment = Alignment.Center) {
        if (page > 1)
            OutlinedIconButton(onClick = onPreviousPage, shape = RoundedCornerShape(8.dp)) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "Previous page")
            }
    }

    Spacer(Modifier.width(12.dp))

    Box(mod, contentAlignment = Alignment.Center) {
        Text(page.toString(), fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
    }

    Spacer(Modifier.width(12.dp))

    Box(mod, contentAlignment = Alignment.Center) {
        if (!lastPage)
            OutlinedIconButton(onClick = onNextPage, shape = RoundedCornerShape(8.dp)) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "Next page")
            }
    }
}
