package com.developerideas.myapplication

import android.app.Application
import androidx.room.Room
import com.developerideas.myapplication.model.database.MovieDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApp : Application() {

    lateinit var db: MovieDatabase
        private set
//
//    override fun onCreate() {
//        super.onCreate()
//
//        db = Room.databaseBuilder(
//            this,
//            MovieDatabase::class.java, "movie-db"
//        ).build()
//    }
}