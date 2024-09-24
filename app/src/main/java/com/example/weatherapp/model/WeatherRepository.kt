package com.example.weatherapp.model

import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {
    suspend fun getWeather(apiKey: String, location: String): WeatherResponse? {
        val response = weatherApi.getCurrentWeather(apiKey, location)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}