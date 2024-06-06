package com.developerideas.myapplication.ui.favorite

import com.developerideas.data.repository.MoviesRepository
import com.developerideas.usecases.DeleteFavoritesMovies
import com.developerideas.usecases.GetFavoritesMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class FavoriteFragmentModule {
    @Provides
    fun findFavoriteMoviesProvider(moviesRepository: MoviesRepository) = GetFavoritesMovies(moviesRepository)

    @Provides
    fun deleteFavoriteMovieProvider(moviesRepository: MoviesRepository) = DeleteFavoritesMovies(moviesRepository)
}