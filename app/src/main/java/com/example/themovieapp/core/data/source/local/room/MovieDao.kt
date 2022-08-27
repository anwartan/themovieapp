package com.example.themovieapp.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.themovieapp.core.data.source.local.entity.FavoriteEntity
import com.example.themovieapp.core.data.source.local.entity.MovieEntity
import com.example.themovieapp.core.data.source.local.entity.MovieFavoriteEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getNowPlayingMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie where id=:id")
    fun getMovie(id: Int): LiveData<MovieEntity?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteTourism(movie: MovieEntity)

    @Query("SELECT * FROM movie where originalTitle like :name ")
    fun searchMoviesByName(name: String): LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM favorite")
    fun getFavoriteMovies(): LiveData<List<MovieFavoriteEntity>>

    @Transaction
    @Query("SELECT * FROM favorite where movieId=:id")
    fun getFavoriteMovie(id: Int): LiveData<MovieFavoriteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favorite: FavoriteEntity)


    @Query("DELETE from favorite where movieId =:id ")
    fun deleteFavoriteMovie(id: Int)

}
