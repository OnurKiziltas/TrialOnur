package com.example.trialonur.features.viewstate

import com.example.trialonur.data.model.*


data class DetailViewState(
    val isLoading: Boolean = false,
    val data: SatelliteDetail? = null,
    val position: Position? = null,
) : IViewState
