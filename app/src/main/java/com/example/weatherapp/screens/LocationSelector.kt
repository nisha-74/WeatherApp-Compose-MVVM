package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LocationSelector(
    locations: List<String>,
    selectedLocation: String,
    onLocationSelector: (String) -> Unit){

    Column() {
        Text(text = "Select Location",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        locations.forEach { locations->
            Row(modifier = Modifier.fillMaxWidth().selectable(
                selected = (locations ==selectedLocation),
                onClick = {
                    onLocationSelector(locations)
                }

            ).padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (locations==selectedLocation),
                    onClick = {onLocationSelector(locations)}

                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = locations)
            }
        }
    }
}