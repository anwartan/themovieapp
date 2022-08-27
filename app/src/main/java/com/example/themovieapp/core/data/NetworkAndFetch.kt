package com.example.themovieapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.themovieapp.core.data.source.remote.network.ApiResponse
import com.example.themovieapp.core.utils.AppExecutors

abstract class NetworkAndFetch<ResultType,RequestType>(private val mExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()


    init {
        result.value = Resource.Loading(null)

        fetchFromNetwork()

    }

    private fun fetchFromNetwork() {
        val apiResource = createCall()

        result.addSource(apiResource) {response->
            result.removeSource(apiResource)
            when(response){
                is ApiResponse.Success ->
                    mExecutors.diskIO().execute{
                        onFetchSuccess(response.data)
                        mExecutors.mainThread().execute{
                            result.value = Resource.Success(mapResponse(response.data))
                        }

                    }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    result.value = Resource.Error(response.errorMessage)
                }
                is ApiResponse.Empty -> {
                    result.value = Resource.Error("EMPTY")
                }

            }
        }


    }



    protected open fun onFetchFailed() {}

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun onFetchSuccess(data: RequestType)

    abstract fun mapResponse(data: RequestType): ResultType

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}