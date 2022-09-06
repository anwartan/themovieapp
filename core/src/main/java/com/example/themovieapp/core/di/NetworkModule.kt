package com.example.themovieapp.core.di

import com.example.themovieapp.core.BuildConfig
import com.example.themovieapp.core.data.source.remote.network.ApiConfig
import com.example.themovieapp.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient():OkHttpClient{

        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, BuildConfig.SSL_PINNING_ONE)
            .add(hostname,BuildConfig.SSL_PINNING_TWO)
            .build()
        val interceptor = Interceptor { chain ->
            val request = chain.request()

            val newUrl = request.url.newBuilder().addQueryParameter(
                ApiConfig.API_KEY_QUERY,
                ApiConfig.API_KEY
            ).build()
            val requestBuilder = request.newBuilder().url(newUrl)

            chain.proceed(requestBuilder.build())
        }
        val loggingInterceptor = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()

    }
    @Singleton
    @Provides
    fun provideApiService(client:OkHttpClient): ApiService {


        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}