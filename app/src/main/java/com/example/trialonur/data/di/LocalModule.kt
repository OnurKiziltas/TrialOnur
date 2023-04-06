package com.example.trialonur.data.di

import android.content.Context
import com.example.trialonur.data.dao.SatelliteDao
import com.example.trialonur.data.db.SatelliteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideSatelliteDatabase(
        @ApplicationContext context: Context
    ): SatelliteDatabase {
        return SatelliteDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideSatalliteDao(appDatabase: SatelliteDatabase): SatelliteDao {
        return appDatabase.satelliteDao()
    }
}