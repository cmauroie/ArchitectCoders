package com.arquitectcoders.usecases

import com.arquitectcoders.domain.Movie
import com.developerideas.data.repository.MoviesRepository

class FindMovieById(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(id: Int): Movie = moviesRepository.findById(id)
}