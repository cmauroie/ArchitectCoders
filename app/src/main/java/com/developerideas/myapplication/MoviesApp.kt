package com.developerideas.myapplication

import android.app.Application

class MoviesApp : Application() {

    /*lateinit var db: MovieDatabase
        private set*/

    override fun onCreate() {
        super.onCreate()

        /*db = Room.databaseBuilder(
            this,
            MovieDatabase::class.java, "movie-db"
        ).build()*/
    }
}