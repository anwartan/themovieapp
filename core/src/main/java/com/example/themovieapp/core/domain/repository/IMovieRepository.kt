package com.example.themovieapp.core.domain.repository

import android.annotation.SuppressLint
import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

@SuppressLint("NamingPattern")
interface IMovieRepository {

    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>

    fun setFavoriteMovie(idMovie: Int, newStatus: Boolean)

    fun getFavoriteMovies(): Flow<List<MovieDetail>>
    fun getMovieDetail(id: Int): Flow<MovieDetail?>

    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
    fun searchMoviesByName(name: String): Flow<List<Movie>>

    fun getWatchMovies(): Flow<List<MovieDetail>>
    fun setWatchMovie(idMovie: Int, newStatus: Boolean)
}