package com.example.complant.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create api class for the OpenWeatherMap Api
 */
class WeatherApi {

    companion object {
        private const val baseUrl = "http://api.openweathermap.org/data/2.5/"

        fun createApi() : WeatherApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val weatherApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return weatherApi.create(WeatherApiService::class.java)
        }
    }
}