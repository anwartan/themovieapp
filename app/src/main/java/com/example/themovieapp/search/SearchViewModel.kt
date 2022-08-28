package com.example.themovieapp.search

import androidx.lifecycle.*
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase):ViewModel() {

    private val searchMovie = MutableLiveData<String>()
    val movie: LiveData<List<Movie>> = Transformations.switchMap(searchMovie) {
        LiveDataReactiveStreams.fromPublisher(movieUseCase.searchMoviesByName(it))
    }

    fun searchMovie(name:String){
        searchMovie.postValue(name)
    }
}