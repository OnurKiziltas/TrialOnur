package com.example.trialonur.domain

import com.example.trialonur.data.DataState
import com.example.trialonur.data.dao.SatelliteDao
import com.example.trialonur.data.model.response.SatelliteDetailResponse
import com.example.trialonur.data.model.entity.SatelliteEntity
import com.example.trialonur.data.model.response.SatelliteListResponse
import com.example.trialonur.data.model.response.SatellitePositionResponse
import com.example.trialonur.data.remote.SatelliteRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor
    (
    private val objectRemoteDataSource: SatelliteRemoteDataSource,
    private val dao: SatelliteDao
) :
    SatelliteRepository {

    override suspend fun getSatelliteList(): Flow<DataState<SatelliteListResponse>> =
        objectRemoteDataSource.getSatelliteList()

    override suspend fun getSatelliteDetail(): Flow<DataState<SatelliteDetailResponse>> =
        objectRemoteDataSource.getSatelliteDetail()

    override suspend fun getPosition(): Flow<DataState<SatellitePositionResponse>> =
        objectRemoteDataSource.getPosition()

    override suspend fun getSatelliteDao(id: Int): SatelliteEntity? = dao.getSatelliteDetail(id)

    override suspend fun saveSatelliteDao(entity: SatelliteEntity) = dao.insert(entity)
}