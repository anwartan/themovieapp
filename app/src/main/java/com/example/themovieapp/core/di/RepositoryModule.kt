package com.example.themovieapp.core.di

import com.example.themovieapp.core.data.repository.MovieRepository
import com.example.themovieapp.core.data.source.local.LocalDataSource
import com.example.themovieapp.core.data.source.remote.RemoteDataSource
import com.example.themovieapp.core.domain.repository.IMovieRepository
import com.example.themovieapp.core.utils.AppExecutors
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//@Module(includes = [NetworkModule::class, DatabaseModule::class])
//abstract class RepositoryModule {
//
//    @Binds
//    abstract fun provideRepository(movieRepository: MovieRepository):IMovieRepository
//
//}

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,localDataSource: LocalDataSource,executors: AppExecutors):IMovieRepository = MovieRepository(remoteDataSource = remoteDataSource,appExecutors = executors,localDataSource = localDataSource)

}