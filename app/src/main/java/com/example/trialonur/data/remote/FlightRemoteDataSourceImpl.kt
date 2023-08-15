package com.example.trialonur.data.remote

import com.example.trialonur.data.DataState
import com.example.trialonur.data.model.response.FlightResponseModel
import com.example.trialonur.data.service.OpenSkyApiService
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlightRemoteDataSourceImpl @Inject constructor(
    private val openSkyApiService: OpenSkyApiService,
) : BaseRemoteDataSource(), FlightRemoteDataSource {
    override suspend fun getFlights(
        lamin: String,
        lomin: String,
        lamax: String,
        lomax: String
    ): Flow<DataState<FlightResponseModel>> =
        getResult { openSkyApiService.getFlights(lamin, lomin, lamax, lomax) }
}
