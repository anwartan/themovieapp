package com.example.themovieapp.core.data.source.local

import com.example.themovieapp.core.data.source.local.entity.FavoriteEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.local.entity.MovieFavoriteEntity
import com.example.themovieapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getNowPlayingMovies(): Flow<List<MovieEntity>> = movieDao.getNowPlayingMovies()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    fun setFavoriteMovie(movieId: Int, newStatus: Boolean) {
        if (!newStatus) {
            movieDao.deleteFavoriteMovie(movieId)
        }else{
            val favoriteEntity = FavoriteEntity(movieId, Date())
            movieDao.insertFavoriteMovie(favoriteEntity)
        }

    }

    fun getMovie(id: Int): Flow<MovieEntity?> = movieDao.getMovie(id)

    fun searchMoviesByName(name: String): Flow<List<MovieEntity>> {
        return movieDao.searchMoviesByName("%$name%")
    }

    fun getFavoriteMovies(): Flow<List<MovieFavoriteEntity>> = movieDao.getFavoriteMovies()

    fun isFavoriteMovie(id: Int): Flow<Boolean> {
        return movieDao.getFavoriteMovie(id).map {
            it.isNotEmpty()
        }

    }

}