package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.network.Book
import com.example.bookshelf.network.BooksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
sealed interface BookUiState {
    data class Success(val books: List<Book>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
    object Start : BookUiState
}

class BookViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Start)
        private set
    fun searchBooks(query: String) {
        if (query.isBlank()) return
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                BookUiState.Success(booksRepository.searchBooks(query))
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookshelfApplication)
                val booksRepository = application.container.booksRepository
                BookViewModel(booksRepository = booksRepository)
            }
        }
    }
}