package com.example.themovieapp.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieFavorite
import com.example.themovieapp.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) :MovieUseCase {
    override fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>> {
        return movieRepository.getNowPlayingMovie()
    }

    override fun setFavoriteMovie(idMovie:Int, newStatus: Boolean) {
        return movieRepository.setFavoriteMovie(idMovie,newStatus)
    }

    override fun getFavoriteMovies(): LiveData<List<MovieFavorite>> {
        return movieRepository.getFavoriteMovies()
    }

    override fun getMovieDetail(id: Int):LiveData<Movie?> {
        return movieRepository.getMovieDetail(id)
    }

    override fun getPopularMovies(): LiveData<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }

    override fun getTopRatedMovies(): LiveData<Resource<List<Movie>>> {
        return movieRepository.getTopRatedMovies()
    }

    override fun searchMoviesByName(name: String): LiveData<List<Movie>> {
        return movieRepository.searchMoviesByName(name)
    }

    override fun getFavoriteMovie(id: Int): LiveData<MovieFavorite?> {
        return movieRepository.getFavoriteMovie(id)
    }


}