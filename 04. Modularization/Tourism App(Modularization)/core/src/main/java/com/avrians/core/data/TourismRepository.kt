package com.avrians.core.data

import androidx.lifecycle.LiveData
import com.avrians.core.data.source.local.LocalDataSource
import com.avrians.core.data.source.remote.RemoteDataSource
import com.avrians.core.data.source.remote.network.ApiResponse
import com.avrians.core.data.source.remote.response.TourismResponse
import com.avrians.core.domain.model.Tourism
import com.avrians.core.domain.repository.ITourismRepository
import com.avrians.core.utils.AppExecutors
import com.avrians.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TourismRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITourismRepository {

    companion object {
        @Volatile
        private var instance: com.avrians.core.data.TourismRepository? = null

//        fun getInstance(
//            remoteData: RemoteDataSource,
//            localData: LocalDataSource,
//            appExecutors: AppExecutors
//        ): TourismRepository =
//            instance ?: synchronized(this) {
//                instance ?: TourismRepository(remoteData, localData, appExecutors)
//            }
    }

    override fun getAllTourism(): Flow<com.avrians.core.data.Resource<List<Tourism>>> =
        object : com.avrians.core.data.NetworkBoundResource<List<Tourism>, List<TourismResponse>>() {
            override fun loadFromDB(): Flow<List<Tourism>> {
                return localDataSource.getAllTourism().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asFlow()

    override fun getFavoriteTourism(): Flow<List<Tourism>> {
        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }

    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}

