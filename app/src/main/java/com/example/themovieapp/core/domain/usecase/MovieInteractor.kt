package com.example.themovieapp.core.domain.usecase

import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieFavorite
import com.example.themovieapp.core.domain.repository.IMovieRepository
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor (private val movieRepository: IMovieRepository) :MovieUseCase {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getNowPlayingMovie()
    }

    override fun setFavoriteMovie(idMovie:Int, newStatus: Boolean) {
        return movieRepository.setFavoriteMovie(idMovie,newStatus)
    }

    override fun getFavoriteMovies(): Flow<List<MovieFavorite>> {
        return movieRepository.getFavoriteMovies()
    }

    override fun getMovieDetail(id: Int):Flow<Movie?> {
        return movieRepository.getMovieDetail(id)
    }

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getTopRatedMovies()
    }

    override fun searchMoviesByName(name: String): Flow<List<Movie>> {
        return movieRepository.searchMoviesByName(name)
    }

    override fun getFavoriteMovie(id: Int): Flow<Boolean> {
        return movieRepository.getFavoriteMovie(id)
    }


}