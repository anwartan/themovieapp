package com.example.themovieapp.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieFavorite

interface MovieUseCase {
    fun getNowPlayingMovies():LiveData<Resource<List<Movie>>>
    fun setFavoriteMovie(idMovie: Int, newStatus: Boolean)
    fun getFavoriteMovies():LiveData<List<MovieFavorite>>
    fun getMovieDetail(id:Int):LiveData<Movie?>
    fun getPopularMovies():LiveData<Resource<List<Movie>>>
    fun getTopRatedMovies():LiveData<Resource<List<Movie>>>
    fun searchMoviesByName(name: String): LiveData<List<Movie>>
    fun getFavoriteMovie(id:Int):LiveData<MovieFavorite?>
}