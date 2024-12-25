package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Env
import com.example.ui.components.Quote
import com.example.viewmodels.FavoriteViewModel
import com.example.viewmodels.getViewModel

@Composable
fun Env.FavoriteScreen(modifier: Modifier = Modifier) {
    val viewModel = getViewModel { FavoriteViewModel(this) }

    val quotes by viewModel.quotes.collectAsState()

    Column(
        modifier
            .fillMaxSize()
            .padding(12.dp, 12.dp, 12.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)

    ) {
        Text("Favorites", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        if (quotes.isEmpty())
            Text("Nothing here :P")
        else
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(quotes) { quote ->
                    Quote(
                        modifier = modifier.fillMaxWidth(),
                        quote = quote,
                        starred = true,
                        onMark = {
                            if (!it) viewModel.onRemoveQuote(quote)
                        }
                    )
                }
            }
    }
}