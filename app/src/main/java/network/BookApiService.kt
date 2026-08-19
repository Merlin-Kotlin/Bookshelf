package com.example.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String = "AIzaSyAEwu7_bPSvby7ocvhCsWOse2FngOLWRFs"
    ): BooksResponse
}