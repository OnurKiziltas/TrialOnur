package com.example.trialonur.domain

import com.example.trialonur.data.DataState
import com.example.trialonur.data.model.response.SatelliteDetailResponse
import com.example.trialonur.data.model.entity.SatelliteEntity
import com.example.trialonur.data.model.response.SatelliteListResponse
import com.example.trialonur.data.model.response.SatellitePositionResponse
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    suspend fun getSatelliteList() : Flow<DataState<SatelliteListResponse>>
    suspend fun getSatelliteDetail() : Flow<DataState<SatelliteDetailResponse>>
    suspend fun getPosition() : Flow<DataState<SatellitePositionResponse>>
    suspend fun getSatelliteDao(id : Int): SatelliteEntity? = null
    suspend fun saveSatelliteDao(entity : SatelliteEntity): Unit
}