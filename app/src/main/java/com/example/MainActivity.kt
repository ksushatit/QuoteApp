package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ui.components.BottomNavigationBar
import com.example.ui.components.NavigationHost
import com.example.ui.theme.QuoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            val env = Env(navController)

            QuoteAppTheme {
                env.ScaffoldApp()
            }
        }
    }
}

@Composable
fun Env.ScaffoldApp() = Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = { BottomNavigationBar() }
) { innerPadding ->
    NavigationHost(innerPadding)
}
