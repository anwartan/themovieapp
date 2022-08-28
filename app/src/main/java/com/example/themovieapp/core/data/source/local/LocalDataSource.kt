package com.example.themovieapp.core.data.source.local

import com.example.themovieapp.core.data.source.local.entity.FavoriteEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.local.entity.MovieFavoriteEntity
import com.example.themovieapp.core.data.source.local.room.MovieDao
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getNowPlayingMovies(): Flowable<List<MovieEntity>> = movieDao.getNowPlayingMovies()

    fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    fun setFavoriteMovie(movieId: Int, newStatus: Boolean) {
        if (!newStatus) {
            movieDao.deleteFavoriteMovie(movieId)
        }else{
            val favoriteEntity = FavoriteEntity(movieId, Date())
            movieDao.insertFavoriteMovie(favoriteEntity)
        }

    }

    fun getMovie(id: Int): Flowable<MovieEntity?> = movieDao.getMovie(id)

    fun searchMoviesByName(name: String): Flowable<List<MovieEntity>> {
        return movieDao.searchMoviesByName("%$name%")
    }

    fun getFavoriteMovies(): Flowable<List<MovieFavoriteEntity>> = movieDao.getFavoriteMovies()

    fun isFavoriteMovie(id: Int): Flowable<Boolean> {
        return movieDao.getFavoriteMovie(id).flatMap { movies ->
            return@flatMap Flowable.just(movies.isNotEmpty())
        }

    }

}