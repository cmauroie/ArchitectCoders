package com.developerideas.myapplication.di

import android.app.Application
import androidx.room.Room
import com.developerideas.data.repository.PermissionChecker
import com.developerideas.data.source.LocalDataSource
import com.developerideas.data.source.LocationDataSource
import com.developerideas.data.source.RemoteDataSource
import com.developerideas.myapplication.R
import com.developerideas.myapplication.model.AndroidPermissionChecker
import com.developerideas.myapplication.model.PlayServicesLocationDataSource
import com.developerideas.myapplication.model.database.MovieDatabase
import com.developerideas.myapplication.model.database.RoomDataSource
import com.developerideas.myapplication.model.server.TheMovieDbDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun databaseProvider(app: Application): MovieDatabase = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        "movie-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: MovieDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = TheMovieDbDataSource()

    @Provides
    fun locationDataSourceProvider(app: Application): LocationDataSource =
        PlayServicesLocationDataSource(app)

    @Provides
    fun permissionCheckerProvider(app: Application): PermissionChecker =
        AndroidPermissionChecker(app)
}