package com.example.faizalipathtask.network.repository

import com.example.faizalipathtask.model.LocationResponse
import com.example.faizalipathtask.network.model.ApiServiceClass
import com.example.faizalipathtask.network.model.Resource
import javax.inject.Inject


class DefaultMainRepository @Inject constructor(
    private val apiService: ApiServiceClass
) : MainRepository {

    private fun <T> handleException(e: Exception): Resource<T> {
        println("kanyeWestApi $e")
        return if (e.toString() == "java.net.SocketTimeoutException: timeout") {
            Resource.Error("Weak internet connection. Try again later.")
        } else if (e.toString().contains("kotlinx.coroutines.JobCancellationException")) {
            Resource.Error("")
        } else if (e.toString().contains("Expected BEGIN_ARRAY but was BEGIN_OBJECT")) {
            Resource.Error("")
        } else {
            Resource.Error("An $e occurred")
        }
    }

    override suspend fun getLatLng(
        lat: String,
        lng: String,
        currant: String,
        hours: String
    ): Resource<LocationResponse> {
        return try {

            val response = apiService.getLatLng(
                lat,
                lng,
                currant,
                hours,
            )
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error("An Error occurred")
            }
        } catch (e: Exception) {
            handleException(e)

        }
    }


}