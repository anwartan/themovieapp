package com.example.themovieapp.core.data.source.local.room

import androidx.room.*
import com.example.themovieapp.core.data.source.local.entity.DetailEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.local.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDetails(details: List<DetailEntity>)

    @Query("SELECT * FROM movie where originalTitle like :name ")
    fun searchMoviesByName(name: String): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM detail where  isFavorite=1")
    fun getFavoriteMovies(): Flow<List<MovieDetailEntity>>

    @Query("UPDATE detail set isWatch=:status where movieId =:id ")
    fun setWatchMovie(id: Int,status:Boolean)

    @Query("UPDATE detail set isFavorite=:status where movieId =:id ")
    fun setFavoriteMovie(id: Int,status:Boolean)

    @Transaction
    @Query("SELECT * FROM detail where isWatch=1")
    fun getWatchlistMovie(): Flow<List<MovieDetailEntity>>
    @Transaction
    @Query("SELECT * FROM detail where movieId=:id")
    fun getDetailMovie(id:Int): Flow<MovieDetailEntity>

}
