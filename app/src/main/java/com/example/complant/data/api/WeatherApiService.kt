package com.example.complant.data.api

import com.example.complant.model.WeatherItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "011b71d6247e109c678b42acafde1018"

/**
 * Interface for the calls of the
 * Weather API
 */
interface WeatherApiService {
    @GET("weather?&units=metric&appid=$API_KEY")
    fun getWeatherByCity(@Query("q") city: String) : Call<WeatherItem>
}