package com.example.themovieapp.core.domain.repository

import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieFavorite
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>

    fun setFavoriteMovie(idMovie: Int, newStatus: Boolean)

    fun getFavoriteMovies(): Flow<List<MovieFavorite>>
    fun getMovieDetail(id: Int): Flow<Movie?>

    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
    fun searchMoviesByName(name: String): Flow<List<Movie>>

    fun getFavoriteMovie(id: Int): Flow<Boolean>

}