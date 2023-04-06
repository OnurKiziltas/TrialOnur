package com.example.trialonur.data.remote

import android.content.Context
import com.example.trialonur.data.DataState
import com.example.trialonur.data.dao.SatelliteDao
import com.example.trialonur.data.model.response.SatelliteDetailResponse
import com.example.trialonur.data.model.entity.SatelliteEntity
import com.example.trialonur.data.model.response.SatelliteListResponse
import com.example.trialonur.data.model.response.SatellitePositionResponse
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SatelliteRemoteDataSourceImpl @Inject constructor(
    private val context: Context,
    private val dao: SatelliteDao
) : BaseRemoteDataSource(), SatelliteRemoteDataSource {

    val listSatellite = object : TypeToken<SatelliteListResponse>() {}.type
    val detailSatellite = object : TypeToken<SatelliteDetailResponse>() {}.type
    val positionSatellite = object : TypeToken<SatellitePositionResponse>() {}.type

    override suspend fun getSatelliteList(): Flow<DataState<SatelliteListResponse>> =
        getResult(context, "satellite_list.json", listSatellite)

    override suspend fun getSatelliteDetail(): Flow<DataState<SatelliteDetailResponse>> =
        getResult(context, "satellite_detail.json", detailSatellite)

    override suspend fun getPosition(): Flow<DataState<SatellitePositionResponse>> =
        getResult(context, "position.json", positionSatellite)

    override suspend fun saveSatelliteDao(entity: SatelliteEntity) = dao.insert(entity)

    override suspend fun getSatelliteDao(id: Int): SatelliteEntity? = dao.getSatelliteDetail(id)

}
