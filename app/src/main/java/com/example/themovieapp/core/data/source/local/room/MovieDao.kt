package com.example.themovieapp.core.data.source.local.room

import androidx.room.*
import com.example.themovieapp.core.data.source.local.entity.FavoriteEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.local.entity.MovieFavoriteEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getNowPlayingMovies(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM movie where id=:id")
    fun getMovie(id: Int): Flowable<MovieEntity?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>) : Completable

    @Query("SELECT * FROM movie where originalTitle like :name ")
    fun searchMoviesByName(name: String): Flowable<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM favorite")
    fun getFavoriteMovies(): Flowable<List<MovieFavoriteEntity>>

    @Transaction
    @Query("SELECT * FROM favorite where movieId=:id")
    fun getFavoriteMovie(id: Int): Flowable<List<MovieFavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favorite: FavoriteEntity)


    @Query("DELETE from favorite where movieId =:id ")
    fun deleteFavoriteMovie(id: Int)

}
