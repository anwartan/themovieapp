package com.example.themovieapp.di

import com.example.themovieapp.core.domain.usecase.MovieInteractor
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUsecase(movieInteractor: MovieInteractor):MovieUseCase
}