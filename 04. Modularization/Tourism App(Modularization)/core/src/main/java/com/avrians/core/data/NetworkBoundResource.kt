package com.avrians.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.avrians.core.data.source.remote.network.ApiResponse

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType>{

    private var result: Flow<com.avrians.core.data.Resource<ResultType>> = flow {
        emit(com.avrians.core.data.Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(com.avrians.core.data.Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { com.avrians.core.data.Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { com.avrians.core.data.Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(com.avrians.core.data.Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { com.avrians.core.data.Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<com.avrians.core.data.Resource<ResultType>> = result
}