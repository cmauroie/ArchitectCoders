package com.developerideas.myapplication.model

import android.app.Application
import com.developerideas.myapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(application: Application) {

    private val apiKey = application.getString(R.string.api_key)
    private val regionRepository = RegionRepository(application)

    suspend fun findPopularMovies() =
        MovieDb.service
            .listPopularMoviesAsync(
                apiKey,
                regionRepository.findLastRegion()
            )

    suspend fun findById(id: Int): Movie = withContext(Dispatchers.IO) {
        Movie(true, "", listOf(1,2,3), 1, "",
            "Pelicula Temp", "", 3.0, "", "",
        "", true, 2.0, 4)

       // db.movieDao().findById(id)
    }
}