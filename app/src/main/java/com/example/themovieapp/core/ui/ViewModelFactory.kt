package com.example.themovieapp.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.core.di.Injection
import com.example.themovieapp.core.domain.usecase.MovieUseCase
import com.example.themovieapp.detail.DetailViewModel
import com.example.themovieapp.di.AppScope
import com.example.themovieapp.favorite.FavoriteViewModel
import com.example.themovieapp.home.HomeViewModel
import com.example.themovieapp.search.SearchViewModel
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) :
    ViewModelProvider.Factory {

//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//
//        fun getInstance(context: Context): ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(
//                    Injection.provideMovieUseCase(context)
//                )
//            }
//    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }

}