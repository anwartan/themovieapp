package com.example.themovieapp.core.data.source.remote.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private const val API_KEY = "2174d146bb9c0eab47529b2e77d6b526"
    private const val BASE_URL="https://api.themoviedb.org/3/"
    private const val API_KEY_QUERY = "api_key"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    fun provideApiService(): ApiService {

        val interceptor = Interceptor { chain ->
            val request = chain.request()

            val newUrl = request.url.newBuilder().addQueryParameter(API_KEY_QUERY,
                API_KEY).build()
            val requestBuilder = request.newBuilder().url(newUrl)

            chain.proceed(requestBuilder.build())
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}