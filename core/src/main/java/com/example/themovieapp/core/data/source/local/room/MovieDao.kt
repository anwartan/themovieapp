package com.example.themovieapp.core.data.source.local.room

import androidx.room.*
import com.example.themovieapp.core.data.source.local.entity.FavoriteEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.local.entity.MovieFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getNowPlayingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where id=:id")
    fun getMovie(id: Int): Flow<MovieEntity?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie where originalTitle like :name ")
    fun searchMoviesByName(name: String): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM favorite")
    fun getFavoriteMovies(): Flow<List<MovieFavoriteEntity>>

    @Transaction
    @Query("SELECT * FROM favorite where movieId=:id")
    fun getFavoriteMovie(id: Int): Flow<List<MovieFavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favorite: FavoriteEntity)


    @Query("DELETE from favorite where movieId =:id ")
    fun deleteFavoriteMovie(id: Int)

}
