package com.example.worldoff1.network

import com.example.worldoff1.model.DriverResponse
import retrofit2.http.GET

interface ApiService {

    @GET("drivers.json")
    suspend fun getDrivers(): DriverResponse
}