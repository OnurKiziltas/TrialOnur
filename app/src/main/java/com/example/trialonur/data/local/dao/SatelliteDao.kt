package com.example.trialonur.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.trialonur.data.local.base.BaseDao
import com.example.trialonur.data.model.entity.SatelliteEntity
import com.example.trialonur.util.Constants

@Dao
interface SatelliteDao : BaseDao<SatelliteEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE id = :detailId")
    suspend fun getSatelliteDetail(detailId: Int): SatelliteEntity?

    @Query("DELETE FROM ${Constants.TABLE_NAME} WHERE id = :detailId")
    suspend fun deleteSatelliteDetailById(detailId: Int)
}