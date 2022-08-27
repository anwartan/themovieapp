package com.example.themovieapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase):ViewModel() {

    private val searchMovie = MutableLiveData<String>()
    val movie: LiveData<List<Movie>> = Transformations.switchMap(searchMovie) {
        movieUseCase.searchMoviesByName(it)
    }

    fun searchMovie(name:String){
        searchMovie.postValue(name)
    }
}