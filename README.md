# Bookshelf

An Android app that searches the Google Books API and displays matching results with cover images, titles, and details.

## Features
- Search books by title, author, or keyword using the Google Books API
- Displays results in a scrollable grid with cover images
- Handles missing cover art gracefully (some books have no image)
- Explicit UI states: start, loading, success, and error

## Architecture
- **Repository pattern** — `BooksRepository` interface with a network-backed implementation, decoupling the ViewModel from Retrofit
- **Manual dependency injection** — an `AppContainer` builds and provides dependencies, held by a custom `Application` class
- **Sealed UI state** — `BookUiState` (`Start` / `Loading` / `Success` / `Error`) drives exhaustive, compiler-checked UI branching
- **Nested JSON parsing** — data classes (`BooksResponse`, `Book`, `VolumeInfo`, `ImageLinks`) map directly to the API's nested response structure
- **User-driven search** — unlike a one-time data fetch, the ViewModel exposes a `searchBooks(query)` function triggered by user input via unidirectional data flow (UI event → ViewModel → state → UI)

## Tech stack
- Kotlin
- Jetpack Compose
- Retrofit + kotlinx.serialization
- Coil (image loading)
- ViewModel + Coroutines