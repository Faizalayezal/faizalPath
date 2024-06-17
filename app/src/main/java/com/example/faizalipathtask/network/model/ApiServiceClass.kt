package com.example.faizalipathtask.network.model


import com.example.faizalipathtask.model.LocationResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiServiceClass {

    @GET("v1/forecast?")
    suspend fun getLatLng(
        @Query("latitude") lat: String?,
        @Query("longitude") lng: String?,
        @Query("current") currant: String?,
        @Query("hourly") hours: String?,

        ): Response<LocationResponse>

}
