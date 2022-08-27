package com.example.themovieapp

import android.app.Application
import com.example.themovieapp.core.di.CoreComponent
import com.example.themovieapp.core.di.DaggerCoreComponent
import com.example.themovieapp.di.AppComponent
import com.example.themovieapp.di.DaggerAppComponent

open class MyApplication:Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}