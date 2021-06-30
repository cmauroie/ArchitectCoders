package com.developerideas.myapplication.model.server

import com.developerideas.myapplication.MoviesApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.developerideas.myapplication.model.database.Movie as DbMovie

class MoviesRepository(application: MoviesApp)  {

    private val db = application.db

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