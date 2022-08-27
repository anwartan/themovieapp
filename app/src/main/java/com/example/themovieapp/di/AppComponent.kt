package com.example.themovieapp.di

import com.example.themovieapp.core.di.CoreComponent
import com.example.themovieapp.detail.DetailActivity
import com.example.themovieapp.favorite.FavoriteFragment
import com.example.themovieapp.home.HomeFragment
import com.example.themovieapp.search.SearchActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class,ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }
    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailActivity)
    fun inject(activity: SearchActivity)

}