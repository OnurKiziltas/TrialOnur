package com.example.trialonur.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class FlightResponseModel(
    var time: Int?,
    var states: List<List<String>>?
)
