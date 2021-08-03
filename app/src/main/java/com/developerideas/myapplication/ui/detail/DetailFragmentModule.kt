package com.developerideas.myapplication.ui.detail


import com.developerideas.data.repository.MoviesRepository
import com.developerideas.usecases.FindMovieById
import com.developerideas.usecases.ToggleMovieFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/*@Module
@InstallIn(ActivityComponent::class)
class DetailFragmentModule {
    @Provides
    @Named("movieId")
    //fun getMovieId(activity: Activity) = activity.intent.getIntExtra(DetailFragment.MOVIE, -1)
    fun getMovieId(detailFragmentArgs: DetailFragmentArgs) = detailFragmentArgs.id

}*/

@Module
@InstallIn(ActivityRetainedComponent::class)
class DetailActivityRetainedModule {

    @Provides
    fun findMovieByIdProvider(moviesRepository: MoviesRepository) = FindMovieById(moviesRepository)

    @Provides
    fun toggleMovieFavoriteProvider(moviesRepository: MoviesRepository) =
        ToggleMovieFavorite(moviesRepository)
}