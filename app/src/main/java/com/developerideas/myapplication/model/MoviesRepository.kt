package com.developerideas.myapplication.model

import android.app.Application
import com.developerideas.myapplication.R

class MoviesRepository(application: Application) {

    private val apiKey = application.getString(R.string.api_key)
    private val regionRepository = RegionRepository(application)

    suspend fun findPopularMovies() =
        MovieDb.service
            .listPopularMoviesAsync(
                apiKey,
                regionRepository.findLastRegion()
            )
}