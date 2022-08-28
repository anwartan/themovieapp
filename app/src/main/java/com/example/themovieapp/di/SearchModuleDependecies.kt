package com.example.themovieapp.di

import com.example.themovieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SearchModuleDependecies {
    fun movieUsecase(): MovieUseCase
}