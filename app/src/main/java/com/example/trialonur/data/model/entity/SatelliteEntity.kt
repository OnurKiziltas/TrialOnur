package com.example.trialonur.data.model.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trialonur.util.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class SatelliteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Constants.COLUMN_ID) val id: Int,
    @ColumnInfo(name = Constants.COLUMN_COST_PER_LAUNC) val costPerLaunch: Int,
    @ColumnInfo(name = Constants.COLUMN_MASS) val mass: Int,
    @ColumnInfo(name = Constants.COLUMN_FIRST_FLIGHT) val firstFlight: String,
    @ColumnInfo(name = Constants.COLUMN_HEIGHT) val height: Int,
)