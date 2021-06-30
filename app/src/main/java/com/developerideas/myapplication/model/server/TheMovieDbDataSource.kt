package com.developerideas.myapplication.model.server

import com.developerideas.data.source.RemoteDataSource
import com.developerideas.domain.Movie
import com.developerideas.myapplication.model.toDomainMovie

class TheMovieDbDataSource : RemoteDataSource {

    override suspend fun getPopularMovies(apiKey: String, region: String): List<Movie> =
        MovieDb.service
            .listPopularMoviesAsync(apiKey, region)
            .results
            .map { it.toDomainMovie() }
}