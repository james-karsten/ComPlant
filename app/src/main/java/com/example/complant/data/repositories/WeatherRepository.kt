package com.example.complant.data.repositories

import com.example.complant.data.api.WeatherApi
import com.example.complant.data.api.WeatherApiService
import com.example.complant.model.WeatherItem

/**
 * Repository for getting the weather of a city
 */
class WeatherRepository {

    private val weatherApi: WeatherApiService = WeatherApi.createApi()

    fun getWeatherByCity(city: String)= weatherApi.getWeatherByCity(city)
}