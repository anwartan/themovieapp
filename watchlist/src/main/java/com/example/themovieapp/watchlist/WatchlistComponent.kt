package com.example.themovieapp.watchlist

import android.content.Context
import com.example.themovieapp.di.WatchlistModuleDependecies
import dagger.BindsInstance
import dagger.Component


@Component(dependencies = [WatchlistModuleDependecies::class])
interface WatchlistComponent {

    fun inject(fragment: WatchlistFragment)
    @Component.Builder
    interface Builder {
        fun content(@BindsInstance context: Context):Builder
        fun appDependencies(watchlistModuleDependecies: WatchlistModuleDependecies):Builder
        fun build():WatchlistComponent
    }
}