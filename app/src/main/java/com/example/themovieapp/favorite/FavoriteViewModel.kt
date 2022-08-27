package com.example.themovieapp.favorite

import androidx.lifecycle.ViewModel
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovies()




}