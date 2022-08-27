package com.example.themovieapp.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.core.di.Injection
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import com.example.themovieapp.detail.DetailViewModel
import com.example.themovieapp.favorite.FavoriteViewModel
import com.example.themovieapp.home.HomeViewModel
import com.example.themovieapp.search.SearchViewModel

class ViewModelFactory private constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideMovieUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) ->{
                SearchViewModel(movieUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}