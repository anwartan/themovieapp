package com.example.themovieapp.core.domain.repository

import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieFavorite
import io.reactivex.Flowable

interface IMovieRepository {

    fun getNowPlayingMovie(): Flowable<Resource<List<Movie>>>

    fun setFavoriteMovie(idMovie: Int, newStatus: Boolean)

    fun getFavoriteMovies(): Flowable<List<MovieFavorite>>
    fun getMovieDetail(id: Int): Flowable<Movie?>

    fun getPopularMovies(): Flowable<Resource<List<Movie>>>
    fun getTopRatedMovies(): Flowable<Resource<List<Movie>>>
    fun searchMoviesByName(name: String): Flowable<List<Movie>>

    fun getFavoriteMovie(id: Int): Flowable<Boolean>

}