package com.example.themovieapp.core.data.source.remote.network

import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    const val API_KEY = "2174d146bb9c0eab47529b2e77d6b526"
    const val BASE_URL="https://api.themoviedb.org/3/"
    const val API_KEY_QUERY = "api_key"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"


    fun provideOkHttpClient():OkHttpClient{
        val interceptor = Interceptor { chain ->
            val request = chain.request()

            val newUrl = request.url.newBuilder().addQueryParameter(
                ApiConfig.API_KEY_QUERY,
                ApiConfig.API_KEY
            ).build()
            val requestBuilder = request.newBuilder().url(newUrl)

            chain.proceed(requestBuilder.build())
        }
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }


    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}