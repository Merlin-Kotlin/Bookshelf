package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.screens.BookViewModel
import com.example.bookshelf.ui.screens.HomeScreen
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookshelfTheme {
                BookshelfApp()
            }
        }
    }
}

@Composable
fun BookshelfApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val bookViewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)
        HomeScreen(
            bookUiState = bookViewModel.bookUiState,
            onSearch = { query -> bookViewModel.searchBooks(query) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}