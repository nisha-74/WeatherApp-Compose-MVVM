package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.weatherapp.viewmodel.WeatherUiState
import com.example.weatherapp.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    /**
     * hiltViewModel(): is a function that allow us
     * to inject a Viewmodel into a composable
     * without the need for a ViewModelProvider
     * or  ViewModelStoreOwner
     */

    viewModel: WeatherViewModel = hiltViewModel()
){
    // Collect stateFlow values as Compose State
    val uiState by viewModel.uiState.collectAsState()
    val location by viewModel.location.collectAsState()
    val selectedLocation by viewModel.selectedLocation.collectAsState()

     Scaffold(
         topBar = {TopAppBar(title =  {Text(text = "Weather App with Hilt")})}
     ) {
         paddingValues ->
         Column(modifier = Modifier.fillMaxSize().padding(paddingValues)
             .padding(16.dp),
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             LocationSelector(
                 locations = location,
                 selectedLocation = selectedLocation,

                 onLocationSelector = { location ->
                     viewModel.selectedLocation(location)
                 },
             )
             Spacer(modifier = Modifier.height(32.dp))

             // Weather Display based on UI State
             when(val state = uiState){
                 is  WeatherUiState.Loading-> {
                     CircularProgressIndicator()
                 }
                 is  WeatherUiState.Success -> {
                     WeatherInfoDisplay(state.weatherInfo)
                 }
                 is WeatherUiState.Error -> {
                     Text(text = state.errorMessage, color = Color.Red)
                 }

             }
         }
     }

}