package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository ): ViewModel() {

    /**
     * UI State
     *  StateFlow : is a state_holder observable flow used with UI state management
     *  It is Hot : it always has a current  value & emits updates to collectors
     *  think of it like a LiveData  Replacement for modern , coroutine- based code
     */
    var _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState : StateFlow<WeatherUiState> = _uiState.asStateFlow()
    private  val _loaction = MutableStateFlow<List<String>>(emptyList())
    val location : StateFlow<List<String>> = _loaction.asStateFlow()

    private  val _selectedLocation = MutableStateFlow<String>("")
    val selectedLocation : StateFlow<String> = _selectedLocation.asStateFlow()

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            val locationList = weatherRepository.getAllLocations()
            _loaction.value= locationList

            if(locationList.isNotEmpty()){
                _selectedLocation.value = locationList[0]
                loadWeatherForLocation(locationList[0])
            }
        }
    }

    private fun loadWeatherForLocation(location: String) {
        viewModelScope.launch {
             _uiState.value= WeatherUiState.Loading
            try {
                //fetch weather data for the selected location
                val weatherInfo = weatherRepository.getWeatherForLocation(location)
                _uiState.value = WeatherUiState.Success(weatherInfo)

            }catch (e: Exception){
                _uiState.value = WeatherUiState.Error("Failed to load weather data")
            }

        }

    }

    fun selectedLocation(location: String) {
        _selectedLocation.value= location
        loadWeatherForLocation(location)
    }

}