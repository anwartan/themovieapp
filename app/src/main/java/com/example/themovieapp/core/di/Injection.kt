package com.example.themovieapp.core.di

import android.content.Context
import com.example.themovieapp.core.data.repository.MovieRepository
import com.example.themovieapp.core.data.source.local.LocalDataSource
import com.example.themovieapp.core.data.source.local.room.MovieDatabase
import com.example.themovieapp.core.data.source.remote.RemoteDataSource
import com.example.themovieapp.core.data.source.remote.network.ApiConfig
import com.example.themovieapp.core.domain.repository.IMovieRepository
import com.example.themovieapp.core.domain.usecase.MovieInteractor
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import com.example.themovieapp.core.utils.AppExecutors

object Injection {

//    private fun provideRepository(context: Context): IMovieRepository {
//        val database = MovieDatabase.getInstance(context)
//        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService(ApiConfig.provideOkHttpClient()))
//        val localDataSource = LocalDataSource.getInstance(database.movieDao())
//        val appExecutors = AppExecutors()
//        return MovieRepository.getInstance(remoteDataSource,localDataSource,appExecutors)
//    }
//
//    fun provideMovieUseCase(context: Context):MovieUseCase{
//        val repository = provideRepository(context)
//        return MovieInteractor(repository)
//    }


}