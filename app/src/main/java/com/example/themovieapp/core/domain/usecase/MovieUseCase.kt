package com.example.themovieapp.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieFavorite
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
    fun setFavoriteMovie(idMovie: Int, newStatus: Boolean)
    fun getFavoriteMovies():Flow<List<MovieFavorite>>
    fun getMovieDetail(id:Int):Flow<Movie?>
    fun getPopularMovies():Flow<Resource<List<Movie>>>
    fun getTopRatedMovies():Flow<Resource<List<Movie>>>
    fun searchMoviesByName(name: String): Flow<List<Movie>>
    fun getFavoriteMovie(id:Int):Flow<Boolean>
}