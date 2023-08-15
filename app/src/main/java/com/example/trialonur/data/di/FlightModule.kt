package com.example.trialonur.data.di

import com.example.trialonur.data.remote.FlightRemoteDataSource
import com.example.trialonur.data.remote.FlightRemoteDataSourceImpl
import com.example.trialonur.data.service.OpenSkyApiService
import com.example.trialonur.data.repository.FlightRepository
import com.example.trialonur.data.repository.FlightRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class FlightModule {
    @Provides
    fun provideOpenSkyApiService(retrofit: Retrofit): OpenSkyApiService =
        retrofit.create(OpenSkyApiService::class.java)

    @Provides
    fun provideSatelliteRemoteDataSource(openSkyApiService: OpenSkyApiService): FlightRemoteDataSource =
        FlightRemoteDataSourceImpl(openSkyApiService)

    @Provides
    fun provideSatelliteRepository(
        satelliteRemoteDataSource: FlightRemoteDataSource
    ): FlightRepository =
        FlightRepositoryImpl(satelliteRemoteDataSource)
}