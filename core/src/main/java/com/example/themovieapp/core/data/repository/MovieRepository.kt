package com.example.themovieapp.core.data.repository


import NetworkBoundResource
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
import com.example.themovieapp.core.utils.mapper.MovieFavoriteMapper
import com.example.themovieapp.core.utils.mapper.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
):IMovieRepository{

    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> {
        return object :NetworkBoundResource<List<Movie>,List<MovieResponse>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getNowPlayingMovies().map {
                    MovieMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getNowPlayingMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                localDataSource.insertMovies(MovieMapper.mapResponsesToEntities(data))
            }

        }.asFlow()
    }

    override fun setFavoriteMovie(idMovie:Int,newStatus:Boolean) {
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteMovie(idMovie, newStatus)
        }
    }

    override fun getFavoriteMovies(): Flow<List<MovieFavorite>> {
        return localDataSource.getFavoriteMovies().map{
            MovieFavoriteMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getMovieDetail(id: Int): Flow<Movie?> {
        return localDataSource.getMovie(id).map{
            it?.let {
                MovieMapper.mapEntityToDomain(it)
            }
        }
    }

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkAndFetch<List<Movie>,List<MovieResponse>>(){


            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun onFetchSuccess(data: List<MovieResponse>){
                val movieList= MovieMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)

            }

            override fun mapResponse(data: List<MovieResponse>): List<Movie> {
                val entity = MovieMapper.mapResponsesToEntities(data)
                return MovieMapper.mapEntitiesToDomain(entity)
            }


        }.asFlow()
    }

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> {
        return object :NetworkAndFetch<List<Movie>,List<MovieResponse>>(){
            override fun mapResponse(data: List<MovieResponse>): List<Movie> {
                val entity = MovieMapper.mapResponsesToEntities(data)
                return MovieMapper.mapEntitiesToDomain(entity)
            }

            override suspend fun onFetchSuccess(data: List<MovieResponse>) {
                val movieList= MovieMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)

            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getTopRatedMovies()
            }

        }.asFlow()
    }

    override fun searchMoviesByName(name: String): Flow<List<Movie>> {
        return localDataSource.searchMoviesByName(name).map{
            MovieMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteMovie(id: Int): Flow<Boolean> {
        return localDataSource.isFavoriteMovie(id)
    }



}