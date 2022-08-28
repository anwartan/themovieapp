package com.example.themovieapp.search

import android.content.Context
import com.example.themovieapp.di.SearchModuleDependecies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [SearchModuleDependecies::class])
interface SearchComponent {

    fun inject(activity: SearchActivity)
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(searchModuleDependecies: SearchModuleDependecies): Builder
        fun build(): SearchComponent
    }

}