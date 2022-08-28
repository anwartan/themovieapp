package com.example.themovieapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.themovieapp.core.data.source.remote.network.ApiResponse
import com.example.themovieapp.core.utils.AppExecutors
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class NetworkAndFetch<ResultType,RequestType>(private val mExecutors: AppExecutors) {
    private val result = PublishSubject.create<Resource<ResultType>>()
    private val mCompositeDisposable = CompositeDisposable()


    init {
        fetchFromNetwork()

    }

    private fun fetchFromNetwork() {
        val apiResource = createCall()
        result.onNext(Resource.Loading(null))

        val response = apiResource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe { apiResponse->
                when(apiResponse){
                    is ApiResponse.Success ->{
                        apiResource.unsubscribeOn(Schedulers.io())
                        onFetchSuccess(apiResponse.data)
                        result.onNext(Resource.Success(mapResponse(apiResponse.data)))
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        result.onNext(Resource.Error(apiResponse.errorMessage))
                    }
                    is ApiResponse.Empty -> {
                        result.onNext(Resource.Error("EMPTY"))
                    }

                }
            }
        mCompositeDisposable.add(response)
    }



    protected open fun onFetchFailed() {}

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    protected abstract fun onFetchSuccess(data: RequestType)

    abstract fun mapResponse(data: RequestType): ResultType

    fun asFlowable(): Flowable<Resource<ResultType>> =
        result.toFlowable(BackpressureStrategy.BUFFER)
}