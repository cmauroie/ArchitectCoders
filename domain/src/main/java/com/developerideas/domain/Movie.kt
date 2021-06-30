package com.developerideas.domain

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    var posterPath: String,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val voteAverage: Double,
    val favorite: Boolean
)