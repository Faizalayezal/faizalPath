package com.example.faizalipathtask.network.repository


import com.example.faizalipathtask.model.LocationResponse
import com.example.faizalipathtask.network.model.Resource

interface MainRepository {


    suspend fun getLatLng(
        lat: String,
        lng: String,
        currant: String,
        hours: String,
    ): Resource<LocationResponse>


}
