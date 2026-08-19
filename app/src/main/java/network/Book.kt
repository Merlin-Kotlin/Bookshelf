package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    val items: List<Book>
)

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String
)