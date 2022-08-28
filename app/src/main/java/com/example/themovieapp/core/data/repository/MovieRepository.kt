package com.example.themovieapp.core.data.repository

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
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
):IMovieRepository{

    override fun getNowPlayingMovie(): Flowable<Resource<List<Movie>>> {
        return object : NetworkAndFetch<List<Movie>,List<MovieResponse>>(appExecutors){
            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> {
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


        }.asFlowable()

    }

    override fun setFavoriteMovie(idMovie:Int,newStatus:Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(idMovie, newStatus) }
    }

    override fun getFavoriteMovies(): Flowable<List<MovieFavorite>> {
        return localDataSource.getFavoriteMovies().map{
            MovieFavoriteMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getMovieDetail(id: Int): Flowable<Movie?> {
        return localDataSource.getMovie(id).map{
            it.let {
                MovieMapper.mapEntityToDomain(it)
            }
        }
    }

    override fun getPopularMovies(): Flowable<Resource<List<Movie>>> {
        return object : NetworkAndFetch<List<Movie>,List<MovieResponse>>(appExecutors){


            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun onFetchSuccess(data: List<MovieResponse>){
                val movieList= MovieMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun mapResponse(data: List<MovieResponse>): List<Movie> {
                val entity = MovieMapper.mapResponsesToEntities(data)
                return MovieMapper.mapEntitiesToDomain(entity)
            }


        }.asFlowable()
    }

    override fun getTopRatedMovies(): Flowable<Resource<List<Movie>>> {
        return object :NetworkAndFetch<List<Movie>,List<MovieResponse>>(appExecutors){
            override fun mapResponse(data: List<MovieResponse>): List<Movie> {
                val entity = MovieMapper.mapResponsesToEntities(data)
                return MovieMapper.mapEntitiesToDomain(entity)
            }

            override fun onFetchSuccess(data: List<MovieResponse>) {
                val movieList= MovieMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getTopRatedMovies()
            }

        }.asFlowable()
    }

    override fun searchMoviesByName(name: String): Flowable<List<Movie>> {
        return localDataSource.searchMoviesByName(name).map{
            MovieMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteMovie(id: Int): Flowable<Boolean> {
        return localDataSource.isFavoriteMovie(id)
    }



}