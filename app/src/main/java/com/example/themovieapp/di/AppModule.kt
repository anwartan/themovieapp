package com.example.themovieapp.di

import com.example.themovieapp.core.domain.usecase.MovieInteractor
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUsecase(movieInteractor: MovieInteractor):MovieUseCase
}