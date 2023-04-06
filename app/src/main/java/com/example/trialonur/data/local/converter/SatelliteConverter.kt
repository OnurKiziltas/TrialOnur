package com.example.trialonur.data.local.converter

import androidx.room.TypeConverter
import com.example.trialonur.util.Utility.fromJson
import com.example.trialonur.util.Utility.toJson

class SatelliteConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}

