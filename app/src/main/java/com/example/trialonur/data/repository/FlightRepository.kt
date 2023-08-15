package com.example.trialonur.data.repository

import com.example.trialonur.data.DataState
import com.example.trialonur.data.model.response.FlightResponseModel
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    suspend fun getFlights(
        lamin: String,
        lomin: String,
        lamax: String,
        lomax: String
    ): Flow<DataState<FlightResponseModel>>
}