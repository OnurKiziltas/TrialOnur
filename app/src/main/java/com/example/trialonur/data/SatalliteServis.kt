package com.example.trialonur.data

import com.example.trialonur.data.model.SatelliteList

interface SatalliteServis {

    suspend fun getSatelliteList(
    ): List<SatelliteList>

}