package com.example.trialonur.data.repository

import com.example.trialonur.data.DataState
import com.example.trialonur.data.model.response.FlightResponseModel
import com.example.trialonur.data.remote.FlightRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FlightRepositoryImpl @Inject constructor
    (
    private val objectRemoteDataSource: FlightRemoteDataSource,
) :
    FlightRepository {
    override suspend fun getFlights(
        lamin: String,
        lomin: String,
        lamax: String,
        lomax: String
    ): Flow<DataState<FlightResponseModel>> = flow {
        emitAll(objectRemoteDataSource.getFlights(lamin, lomin, lamax, lomax))
    }
}