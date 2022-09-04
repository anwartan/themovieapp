package com.example.themovieapp.core.domain.usecase

import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
    fun setFavoriteMovie(idMovie: Int, newStatus: Boolean)
    fun getFavoriteMovies():Flow<List<MovieDetail>>
    fun getMovieDetail(id:Int):Flow<MovieDetail?>
    fun getPopularMovies():Flow<Resource<List<Movie>>>
    fun getTopRatedMovies():Flow<Resource<List<Movie>>>
    fun searchMoviesByName(name: String): Flow<List<Movie>>
    fun getWatchMovies():Flow<List<MovieDetail>>
    fun setWatchMovies(id:Int, newStatus: Boolean)
}