package com.example.faizalipathtask.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faizalipathtask.model.LocationResponse
import com.example.faizalipathtask.network.model.Resource
import com.example.faizalipathtask.network.repository.MainRepository
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    val repository: MainRepository,
    application: Application
) :
    BaseViewModel(application) {

    init {
        Places.initialize(context, "AIzaSyBa0bj1AU-_R1woXIgqGXN0u3kQmppkP9A")
    }

    val field = listOf(
        Place.Field.ID,
        Place.Field.NAME,
        Place.Field.ADDRESS,
        Place.Field.LAT_LNG,
    )

    private val _locationStateFlow = MutableStateFlow<LocationResponse?>(null)
    val locationStateFlow: StateFlow<LocationResponse?> = _locationStateFlow.asStateFlow()

    fun sendEmailOtp(
        lat: String,
        lng: String,
        onComplete: (status: Boolean) -> Unit

    ) =
        viewModelScope.launch {
            onComplete.invoke(false)

            when (val data = repository.getLatLng(lat,lng,"temperature_2m,wind_speed_10m","temperature_2m,relative_humidity_2m,wind_speed_10m")) {
                is Resource.Error -> {
                }

                is Resource.Success -> {
                    _locationStateFlow.value=data.data
                    onComplete.invoke(true)

                }
            }
        }
}
