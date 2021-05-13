package com.developerideas.myapplication.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.developerideas.myapplication.model.database.Movie
import com.developerideas.myapplication.model.database.MovieDao

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}