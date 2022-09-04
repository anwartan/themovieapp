package com.example.themovieapp.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(movieUseCase: MovieUseCase) :ViewModel() {
    val watchlistMovies = movieUseCase.getWatchMovies().asLiveData()
}