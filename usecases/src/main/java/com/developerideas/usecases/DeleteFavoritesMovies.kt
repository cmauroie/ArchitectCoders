package com.developerideas.usecases

import com.developerideas.data.repository.MoviesRepository
import com.developerideas.domain.Movie

class DeleteFavoritesMovies(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(movie: Movie) =
        movie.apply {
            copy(favorite = !favorite)
                .also { moviesRepository.update(it) }
        }
}