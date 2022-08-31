package com.example.themovieapp.favorite

import android.content.Context
import com.example.themovieapp.di.FavoriteModuleDependecies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependecies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependecies: FavoriteModuleDependecies): Builder
        fun build(): FavoriteComponent
    }

}