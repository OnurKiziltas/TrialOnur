package com.example.trialonur.data.model

import com.google.gson.annotations.SerializedName


data class SatelliteDetail(
    val id : Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch : Int,
    @SerializedName("first_flight")
    val firstFlight : String,
    val height : Int,
    val mass : Int
)
