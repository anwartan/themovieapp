package com.example.themovieapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.themovieapp.core.data.source.local.entity.DetailEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.utils.DateConverter

@Database(entities = [MovieEntity::class,DetailEntity::class], version = 6, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MovieDatabase: RoomDatabase(){

    abstract fun movieDao():MovieDao
}