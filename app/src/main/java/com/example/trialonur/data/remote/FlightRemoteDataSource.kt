package com.example.trialonur.data.remote

import com.example.trialonur.data.DataState
import com.example.trialonur.data.model.response.FlightResponseModel
import kotlinx.coroutines.flow.Flow

interface FlightRemoteDataSource {
    suspend fun getFlights(
        lamin: String,
        lomin: String,
        lamax: String,
        lomax: String
    ): Flow<DataState<FlightResponseModel>>
}