package com.example.themovieapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase):ViewModel() {
    val movie = LiveDataReactiveStreams.fromPublisher(movieUseCase.getNowPlayingMovies())

    val popularMovie = LiveDataReactiveStreams.fromPublisher(movieUseCase.getPopularMovies())

    val topRatedMovie = LiveDataReactiveStreams.fromPublisher(movieUseCase.getTopRatedMovies())
}