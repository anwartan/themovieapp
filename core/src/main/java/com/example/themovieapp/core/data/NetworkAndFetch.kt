package com.example.themovieapp.core.data

import com.example.themovieapp.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


abstract class NetworkAndFetch<ResultType,RequestType> {

    private var result : Flow<Resource<ResultType>> = flow {

        emit(Resource.Loading<ResultType>())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success ->{
                onFetchSuccess(apiResponse.data)
                emit(Resource.Success(mapResponse(apiResponse.data)))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error<ResultType>("EMPTY"))
            }
        }
    }


    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun onFetchSuccess(data: RequestType)

    protected abstract fun mapResponse(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> =result
}