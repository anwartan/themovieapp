package com.example.themovieapp.core.di

import com.example.themovieapp.core.data.repository.MovieRepository
import com.example.themovieapp.core.data.source.local.LocalDataSource
import com.example.themovieapp.core.data.source.remote.RemoteDataSource
import com.example.themovieapp.core.domain.repository.IMovieRepository
import com.example.themovieapp.core.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        executors: AppExecutors
    ): IMovieRepository = MovieRepository(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
        appExecutors = executors
    )

}