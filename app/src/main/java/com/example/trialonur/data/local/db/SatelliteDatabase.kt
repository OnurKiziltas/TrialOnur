package com.example.trialonur.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trialonur.data.dao.SatelliteDao
import com.example.trialonur.data.local.converter.SatelliteConverter
import com.example.trialonur.data.model.entity.SatelliteEntity
import com.example.trialonur.util.Constants

@Database(
    entities = [SatelliteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SatelliteConverter::class)
abstract class SatelliteDatabase : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDao

    companion object {
        @Volatile
        private var instance: SatelliteDatabase? = null

        fun getDatabase(context: Context): SatelliteDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, SatelliteDatabase::class.java, Constants.TABLE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}