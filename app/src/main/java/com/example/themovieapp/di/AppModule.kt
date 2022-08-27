package com.example.themovieapp.di

import com.example.themovieapp.core.domain.usecase.MovieInteractor
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMovieUsecase(movieInteractor: MovieInteractor):MovieUseCase
}