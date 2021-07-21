package com.developerideas.myapplication.ui.main

import com.developerideas.data.repository.MoviesRepository
import com.developerideas.usecases.GetPopularMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainFragmentModule {

    @Provides
    fun getPopularMoviesProvider(moviesRepository: MoviesRepository) = GetPopularMovies(moviesRepository)
}