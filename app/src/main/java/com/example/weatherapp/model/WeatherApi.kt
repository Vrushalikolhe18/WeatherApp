package com.example.weatherapp.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") apikey : String,
        @Query("q") city : String,
        @Query("aqi") aqi: String = "no"
    ): Response<WeatherResponse>

}