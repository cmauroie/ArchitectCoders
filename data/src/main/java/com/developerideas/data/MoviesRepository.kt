package com.developerideas.data

import com.arquitectcoders.domain.Movie

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val regionRepository: RegionRepository,
    private val apiKey: String
) {

    fun suspendPopularMovies(): List<Movie> {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.getPopularMovies(apiKey, regionRepository.findLastRegion())
            localDataSource.saveMovies(movies)
        }
        return localDataSource.getPopularMovies()
    }


}

interface RemoteDataSource {
    fun getPopularMovies(apiKey: String, findLastRegion: String): List<Movie>

}

interface LocalDataSource {
    fun isEmpty(): Boolean
    fun saveMovies(movies: List<Movie>)
    fun getPopularMovies(): List<Movie>

}
