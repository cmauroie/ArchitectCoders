package com.developerideas.myapplication.model.server

import com.developerideas.myapplication.MoviesApp
import com.developerideas.myapplication.R
import com.developerideas.myapplication.model.RegionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.developerideas.myapplication.model.database.Movie as DbMovie
import com.developerideas.myapplication.model.server.Movie as ServerMovie

class MoviesRepository(application: MoviesApp)  {

    private val apiKey = application.getString(R.string.api_key)
    private val regionRepository = RegionRepository(application)
    private val db = application.db

    /*suspend fun findPopularMovies(): List<DbMovie> = withContext(Dispatchers.IO) {
        with(db.movieDao()) {
            if(movieCount() <= 0) {
                val movies = MovieDb.service
                    .listPopularMoviesAsync(apiKey, regionRepository.findLastRegion())
                    .results

                insertMovies(movies = movies.map(ServerMovie::convertToDbMovie))
            }

            getAll()
        }
    }*/

    suspend fun findById(id: Int): DbMovie = withContext(Dispatchers.IO) {
        db.movieDao().findById(id)
    }

    suspend fun update(movie: DbMovie) = withContext(Dispatchers.IO) {
        db.movieDao().updateMovie(movie)
    }

    suspend fun getFavorites() : List<DbMovie> = withContext(Dispatchers.IO) {
        db.movieDao().getFavorites()
    }

    suspend fun deleteFavorite(idMovie: Int) = withContext(Dispatchers.IO) {
        db.movieDao().deleteMovie(idMovie)
    }

}

private fun ServerMovie.convertToDbMovie() = DbMovie(
    0,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath ?: posterPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    false
)