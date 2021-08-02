package com.developerideas.usecases

import com.developerideas.domain.Movie
import com.developerideas.data.repository.MoviesRepository

class GetFavoritesMovies(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(): List<Movie> {
        return moviesRepository.getFavorites()
    }
}