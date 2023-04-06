package com.example.trialonur.data.di

import android.content.Context
import com.example.trialonur.data.dao.SatelliteDao
import com.example.trialonur.data.remote.SatelliteRemoteDataSource
import com.example.trialonur.data.remote.SatelliteRemoteDataSourceImpl
import com.example.trialonur.domain.SatelliteRepository
import com.example.trialonur.domain.SatelliteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class SatelliteModule {

    @Provides
    fun provideSatelliteRemoteDataSource(
        @ApplicationContext context: Context,
        dao: SatelliteDao
    ): SatelliteRemoteDataSource =
        SatelliteRemoteDataSourceImpl(context, dao)

    @Provides
    fun provideSatelliteRepository(
        satelliteRemoteDataSource: SatelliteRemoteDataSource,
        dao: SatelliteDao
    ): SatelliteRepository =
        SatelliteRepositoryImpl(satelliteRemoteDataSource, dao)

}
