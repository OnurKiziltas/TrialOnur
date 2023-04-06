package com.example.trialonur.features.viewstate

import com.example.trialonur.data.model.response.SatelliteListResponse

data class ObjectViewState(
    val isLoading: Boolean = false,
    val data: SatelliteListResponse? = null,
) : IViewState