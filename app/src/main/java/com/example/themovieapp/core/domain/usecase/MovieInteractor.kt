package com.example.themovieapp.core.domain.usecase

import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieFavorite
import com.example.themovieapp.core.domain.repository.IMovieRepository
import io.reactivex.Flowable
import javax.inject.Inject

class MovieInteractor @Inject constructor (private val movieRepository: IMovieRepository) :MovieUseCase {
    override fun getNowPlayingMovies(): Flowable<Resource<List<Movie>>> {
        return movieRepository.getNowPlayingMovie()
    }

    override fun setFavoriteMovie(idMovie:Int, newStatus: Boolean) {
        return movieRepository.setFavoriteMovie(idMovie,newStatus)
    }

    override fun getFavoriteMovies(): Flowable<List<MovieFavorite>> {
        return movieRepository.getFavoriteMovies()
    }

    override fun getMovieDetail(id: Int):Flowable<Movie?> {
        return movieRepository.getMovieDetail(id)
    }

    override fun getPopularMovies(): Flowable<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }

    override fun getTopRatedMovies(): Flowable<Resource<List<Movie>>> {
        return movieRepository.getTopRatedMovies()
    }

    override fun searchMoviesByName(name: String): Flowable<List<Movie>> {
        return movieRepository.searchMoviesByName(name)
    }

    override fun getFavoriteMovie(id: Int): Flowable<Boolean> {
        return movieRepository.getFavoriteMovie(id)
    }


}