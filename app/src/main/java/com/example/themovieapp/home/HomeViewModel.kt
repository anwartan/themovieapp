package com.example.themovieapp.home

import androidx.lifecycle.ViewModel
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase):ViewModel() {
    val movie =  movieUseCase.getNowPlayingMovies()

    val popularMovie = movieUseCase.getPopularMovies()

    val topRatedMovie = movieUseCase.getTopRatedMovies()
}