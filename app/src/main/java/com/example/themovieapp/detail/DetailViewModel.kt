package com.example.themovieapp.detail

import androidx.lifecycle.*
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase):ViewModel() {

    private val idMovie = MutableLiveData<Int>()


    val detailMovie: LiveData<Movie?> = Transformations.switchMap(idMovie) {
        movieUseCase.getMovieDetail(it).asLiveData()
    }

    val isFavoriteMovie: LiveData<Boolean> = Transformations.switchMap(idMovie){
        movieUseCase.getFavoriteMovie(it).asLiveData()
    }


    fun getMovieDetail(id:Int){
        idMovie.postValue(id)
    }

    fun setFavoriteMovie(newStatus: Boolean) {
        idMovie.value?.let {
            movieUseCase.setFavoriteMovie(idMovie = it, newStatus)
        }

    }

}