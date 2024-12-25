package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.Quote

@Composable
fun Quote(
    modifier: Modifier = Modifier,
    quote: Quote,
    starred: Boolean = false,
    onMark: (Boolean) -> Unit = {}
) {
    var isStarred by remember { mutableStateOf(false) }
    isStarred = starred

    Card(modifier) {
        Column(Modifier.padding(12.dp)) {
            Text(quote.body, Modifier.padding(bottom = 8.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("© ${quote.author}", fontWeight = FontWeight.SemiBold)
                IconButton(
                    onClick = {
                        isStarred = !isStarred
                        onMark(isStarred)
                    },
                    modifier = Modifier.height(18.dp),
                ) {
                    if (!isStarred)
                        Icon(Icons.Outlined.ThumbUp, "Mark as favorite")
                    else
                        Icon(Icons.Filled.ThumbUp, "Unmark as favorite")
                }
            }
        }
    }
}