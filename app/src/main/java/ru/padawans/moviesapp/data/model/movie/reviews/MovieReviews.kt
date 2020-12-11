package ru.padawans.moviesapp.data.model.movie.reviews



class MovieReviews(
    val id: Int,
    val page: Int,
    val totalPages: Int,
    val results: List<ResultsItem>,
    val totalResults: Int
)


