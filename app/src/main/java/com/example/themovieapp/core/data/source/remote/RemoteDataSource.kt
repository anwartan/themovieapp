package com.example.themovieapp.core.data.source.remote

import android.util.Log
import com.example.themovieapp.core.data.source.remote.network.ApiResponse
import com.example.themovieapp.core.data.source.remote.network.ApiService
import com.example.themovieapp.core.data.source.remote.response.MovieResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){


    fun getNowPlayingMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()

        //get data from remote api
        val client = apiService.getNowPlayingMovies()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({response ->
                val dataArray = response.results
                resultData.onNext(if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            },{error->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getPopularMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()

        //get data from remote api
        val client = apiService.getPopularMovies()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val dataArray = it.results
                resultData.onNext(if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty)

            },{error ->

                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.message.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getTopRatedMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieResponse>>>()

        //get data from remote api
        val client = apiService.getTopRatedMovies()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val dataArray = it.results
                resultData.onNext(if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty)

            },{error->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.message.toString())

            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}