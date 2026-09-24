package com.example.weatherapp.repository

import com.example.weatherapp.model.WeatherInfo

/**
 * interface for the repository that provides whether data
 * this is what we'll inject
 * using an interface allows us to swap implementations(like for testing )
 */

interface WeatherRepository {

    fun getWeatherForLocation(location:String) :  WeatherInfo

    fun getAllLocations(): List<String>
}