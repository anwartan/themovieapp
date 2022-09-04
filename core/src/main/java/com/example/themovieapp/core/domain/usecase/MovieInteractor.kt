package com.example.themovieapp.core.domain.usecase

import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieDetail
import com.example.themovieapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor (private val movieRepository: IMovieRepository) :MovieUseCase {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getNowPlayingMovie()
    }

    override fun setFavoriteMovie(idMovie:Int, newStatus: Boolean) {
        return movieRepository.setFavoriteMovie(idMovie,newStatus)
    }

    override fun getFavoriteMovies(): Flow<List<MovieDetail>> {
        return movieRepository.getFavoriteMovies()
    }

    override fun getMovieDetail(id: Int):Flow<MovieDetail?> {
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


    override fun getWatchMovies(): Flow<List<MovieDetail>> {
        return movieRepository.getWatchMovies()
    }

    override fun setWatchMovies(id: Int, newStatus: Boolean) {
        return movieRepository.setWatchMovie(id,newStatus)
    }


}