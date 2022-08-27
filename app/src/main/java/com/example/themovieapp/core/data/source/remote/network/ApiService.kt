package com.example.themovieapp.core.data.source.remote.network

import com.example.themovieapp.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(): Call<ListMovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(): Call<ListMovieResponse>
    @GET("movie/top_rated")
    fun getTopRatedMovies(): Call<ListMovieResponse>
}