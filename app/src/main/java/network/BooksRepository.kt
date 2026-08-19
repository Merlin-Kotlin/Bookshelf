package com.example.bookshelf.network

interface BooksRepository {
    suspend fun searchBooks(query: String): List<Book>
}

class NetworkBooksRepository(
    private val bookApiService: BookApiService
) : BooksRepository {
    override suspend fun searchBooks(query: String): List<Book> =
        bookApiService.searchBooks(query).items
}