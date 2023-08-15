package com.example.trialonur.data.service

import com.example.trialonur.data.model.response.FlightResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenSkyApiService {

    @GET("states/all")
    suspend fun getFlights(
        @Query("lamin") lamin: String,
        @Query("lomin") lomin: String,
        @Query("lamax") lamax: String,
        @Query("lomax") lomax: String
    ): Response<FlightResponseModel>

}