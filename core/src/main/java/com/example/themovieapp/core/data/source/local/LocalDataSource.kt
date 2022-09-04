package com.example.themovieapp.core.data.source.local

import com.example.themovieapp.core.data.source.local.entity.DetailEntity
import com.example.themovieapp.core.data.source.local.entity.MovieDetailEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    suspend fun insertMovies(movieList: List<MovieEntity>) {
        val listDetail = movieList.map {
            DetailEntity(it.id, isFavorite = false, isWatch = false, createAt = Date())
        }
        movieDao.insertMovies(movieList)
        movieDao.insertDetails(listDetail)
    }

    fun setFavoriteMovie(movieId: Int, newStatus: Boolean) = movieDao.setFavoriteMovie(movieId,newStatus)

    fun getMovieDetail(id: Int): Flow<MovieDetailEntity?> = movieDao.getDetailMovie(id)

    fun searchMoviesByName(name: String): Flow<List<MovieEntity>> {
        return movieDao.searchMoviesByName("%$name%")
    }

    fun getFavoriteMovies(): Flow<List<MovieDetailEntity>> = movieDao.getFavoriteMovies()

    fun getWatchMovies():Flow<List<MovieDetailEntity>> = movieDao.getWatchlistMovie()

    fun setWatchMovies(movieId:Int, newStatus: Boolean) = movieDao.setWatchMovie(movieId,newStatus)

}