package com.example.themovieapp.home

import androidx.lifecycle.ViewModel
import com.example.themovieapp.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase):ViewModel() {
    val movie =  movieUseCase.getNowPlayingMovies()

    val popularMovie = movieUseCase.getPopularMovies()

    val topRatedMovie = movieUseCase.getTopRatedMovies()
}