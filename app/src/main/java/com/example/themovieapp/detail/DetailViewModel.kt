package com.example.themovieapp.detail

import androidx.lifecycle.*
import com.example.themovieapp.core.domain.model.MovieDetail
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase):ViewModel() {

    private val idMovie = MutableLiveData<Int>()


    val detailMovie: LiveData<MovieDetail?> = Transformations.switchMap(idMovie) {
        movieUseCase.getMovieDetail(it).asLiveData()
    }




    fun getMovieDetail(id:Int){
        idMovie.postValue(id)
    }

    fun setFavoriteMovie(newStatus: Boolean) {
        idMovie.value?.let {
            movieUseCase.setFavoriteMovie(idMovie = it, newStatus)
        }

    }

    fun setWatchMovie( newStatus: Boolean) {
        idMovie.value?.let {
            movieUseCase.setWatchMovies(id = it,newStatus)
        }
    }

}