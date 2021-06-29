package com.arquitectcoders.usecases

import com.arquitectcoders.domain.Movie
import com.developerideas.data.repository.MoviesRepository

class GetPopularMovies(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(): List<Movie> {
        return moviesRepository.getPopularMovies()
    }
}