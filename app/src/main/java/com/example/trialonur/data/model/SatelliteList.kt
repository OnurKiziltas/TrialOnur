package com.example.trialonur.data.model

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class SatelliteList(
    val id : Int,
    val active : Boolean,
    val name : String
): Parcelable {
    companion object {
        fun create(jsonString: String): SatelliteList? {
            return try {
                Gson().fromJson(jsonString, SatelliteList::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}
