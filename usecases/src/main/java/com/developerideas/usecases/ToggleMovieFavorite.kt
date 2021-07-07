package com.developerideas.usecases

import com.developerideas.domain.Movie
import com.developerideas.data.repository.MoviesRepository

class ToggleMovieFavorite(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(movie: Movie): Movie = with(movie) {
        copy(favorite = !favorite).also { moviesRepository.update(it) }
    }
}