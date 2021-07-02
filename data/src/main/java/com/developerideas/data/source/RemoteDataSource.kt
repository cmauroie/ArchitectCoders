package com.developerideas.data.source

import com.arquitectcoders.domain.Movie

interface RemoteDataSource {
    fun getPopularMovies(apiKey: String, findLastRegion: String): List<Movie>
}