package com.example.themovieapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.themovieapp.core.data.source.local.entity.FavoriteEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.local.entity.MovieFavoriteEntity
import com.example.themovieapp.core.data.source.local.room.MovieDao
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

//    companion object {
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(movieDao: MovieDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(movieDao)
//            }
//    }

    fun getNowPlayingMovies(): LiveData<List<MovieEntity>> = movieDao.getNowPlayingMovies()

    fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    fun setFavoriteMovie(movieId: Int, newStatus: Boolean) {
        if (!newStatus) {
            movieDao.deleteFavoriteMovie(movieId)
        }else{
            val favoriteEntity = FavoriteEntity(movieId, Date())
            movieDao.insertFavoriteMovie(favoriteEntity)
        }

    }

    fun getMovie(id: Int): LiveData<MovieEntity?> = movieDao.getMovie(id)

    fun searchMoviesByName(name: String): LiveData<List<MovieEntity>> {
        return movieDao.searchMoviesByName("%$name%")
    }

    fun getFavoriteMovies(): LiveData<List<MovieFavoriteEntity>> = movieDao.getFavoriteMovies()

    fun getFavoriteMovie(id: Int): LiveData<MovieFavoriteEntity?> {
        return movieDao.getFavoriteMovie(id)
    }

}