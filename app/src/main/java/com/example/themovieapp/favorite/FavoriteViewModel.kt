package com.example.themovieapp.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = LiveDataReactiveStreams.fromPublisher(movieUseCase.getFavoriteMovies())




}