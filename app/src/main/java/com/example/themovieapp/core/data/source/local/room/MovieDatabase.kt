package com.example.themovieapp.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.themovieapp.core.data.source.local.entity.FavoriteEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.utils.DateConverter

@Database(entities = [MovieEntity::class,FavoriteEntity::class], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MovieDatabase: RoomDatabase(){

    abstract fun movieDao():MovieDao
//    companion object {
//        @Volatile
//        private var INSTANCE: MovieDatabase? = null
//
//        fun getInstance(context: Context): MovieDatabase =
//            INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MovieDatabase::class.java,
//                    "Movie.db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//    }
}