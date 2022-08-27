package com.example.themovieapp.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.themovieapp.core.data.NetworkAndFetch
import com.example.themovieapp.core.data.Resource
import com.example.themovieapp.core.data.source.local.LocalDataSource
import com.example.themovieapp.core.data.source.remote.RemoteDataSource
import com.example.themovieapp.core.data.source.remote.network.ApiResponse
import com.example.themovieapp.core.data.source.remote.response.MovieResponse
import com.example.themovieapp.core.domain.model.Movie
import com.example.themovieapp.core.domain.model.MovieFavorite
import com.example.themovieapp.core.domain.repository.IMovieRepository
import com.example.themovieapp.core.utils.AppExecutors
import com.example.themovieapp.core.utils.Mapper.MovieFavoriteMapper
import com.example.themovieapp.core.utils.Mapper.MovieMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
):IMovieRepository{
//    companion object {
//        @Volatile
//        private var instance: MovieRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSource,
//            localDataSource: LocalDataSource,
//            appExecutors: AppExecutors
//        ): MovieRepository =
//            instance ?: synchronized(this) {
//                instance ?: MovieRepository(remoteData,localDataSource,appExecutors)
//            }
//    }

    override fun getNowPlayingMovie(): LiveData<Resource<List<Movie>>> {
        return object : NetworkAndFetch<List<Movie>,List<MovieResponse>>(appExecutors){
            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getNowPlayingMovies()
            }

            override fun onFetchSuccess(data: List<MovieResponse>) {
                val movieList= MovieMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }

            override fun mapResponse(data: List<MovieResponse>): List<Movie> {
                val entity = MovieMapper.mapResponsesToEntities(data)
                return MovieMapper.mapEntitiesToDomain(entity)
            }


        }.asLiveData()
    }

    override fun setFavoriteMovie(idMovie:Int,newStatus:Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(idMovie, newStatus) }
    }

    override fun getFavoriteMovies(): LiveData<List<MovieFavorite>> {
        return Transformations.map(localDataSource.getFavoriteMovies()){
            MovieFavoriteMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getMovieDetail(id: Int): LiveData<Movie?> {
        return Transformations.map(localDataSource.getMovie(id)){
            it?.let {
                MovieMapper.mapEntityToDomain(it)
            }
        }
    }

    override fun getPopularMovies(): LiveData<Resource<List<Movie>>> {
        return object : NetworkAndFetch<List<Movie>,List<MovieResponse>>(appExecutors){


            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun onFetchSuccess(data: List<MovieResponse>){
                val movieList= MovieMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }

            override fun mapResponse(data: List<MovieResponse>): List<Movie> {
                val entity = MovieMapper.mapResponsesToEntities(data)
                return MovieMapper.mapEntitiesToDomain(entity)
            }


        }.asLiveData()
    }

    override fun getTopRatedMovies(): LiveData<Resource<List<Movie>>> {
        return object :NetworkAndFetch<List<Movie>,List<MovieResponse>>(appExecutors){
            override fun mapResponse(data: List<MovieResponse>): List<Movie> {
                val entity = MovieMapper.mapResponsesToEntities(data)
                return MovieMapper.mapEntitiesToDomain(entity)
            }

            override fun onFetchSuccess(data: List<MovieResponse>) {
                val movieList= MovieMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getTopRatedMovies()
            }

        }.asLiveData()
    }

    override fun searchMoviesByName(name: String): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.searchMoviesByName(name)){
            MovieMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteMovie(id: Int): LiveData<MovieFavorite?> {
        return Transformations.map(localDataSource.getFavoriteMovie(id)){
            it?.let {
                MovieFavoriteMapper.mapEntityToDomain(it)
            }
        }
    }


}