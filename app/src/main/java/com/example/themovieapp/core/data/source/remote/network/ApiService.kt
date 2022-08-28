package com.example.themovieapp.core.data.source.remote.network

import com.example.themovieapp.core.data.source.remote.response.ListMovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Flowable<ListMovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(): Flowable<ListMovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Flowable<ListMovieResponse>
}