package com.example.trialonur.features.viewstate

import com.example.trialonur.data.model.States
import com.example.trialonur.data.model.response.FlightResponseModel

data class ObjectViewState(
    val isLoading: Boolean = false,
    val data: List<States>? = null,
) : IViewState