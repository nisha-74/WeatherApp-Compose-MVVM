package com.example.weatherapp.di

import com.example.weatherapp.repository.WeatherRepoImp
import com.example.weatherapp.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt Module: used to tell Hilt how to provide
 * dependency that it can't figure out on its own
 * Helps Hilt know hot to bind interfaces to their
 * implementations
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /**
     * @Bind : tells hilt which implementation to use for an interface
     * @Singleton: Makes sure there's only one instance throughout the app
     */

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepoImp: WeatherRepoImp
    ): WeatherRepository
}