package com.example.themovieapp.favorite

import androidx.lifecycle.ViewModel
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovies()




}