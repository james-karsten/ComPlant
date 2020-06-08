package com.example.complant.PlantStatistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.complant.data.repositories.DayRepository
import com.example.complant.data.repositories.PlantRepository
import com.example.complant.data.repositories.WeatherRepository
import com.example.complant.model.Day
import com.example.complant.model.Plant
import com.example.complant.model.PlantWithDays
import com.example.complant.model.WeatherItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class PlantStatisticsActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val plantRepository = PlantRepository(application.applicationContext)
    val weatherItem = MutableLiveData<WeatherItem>()
    private val error = MutableLiveData<String>()

    private val weatherRepository = WeatherRepository()
    private val dayRepository = DayRepository(application.applicationContext)
    var plantsAndDays: LiveData<List<PlantWithDays>> = plantRepository.getPlantsAndDays()

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun updatePlant(plant: Plant) {
        ioScope.launch {
            plantRepository.updatePlant(plant)
        }
    }

    fun insertDay(day: Day) {
        ioScope.launch {
            dayRepository.insertDay(day)
        }
    }

    fun updateDay(day: Day) {
        ioScope.launch {
            dayRepository.updatePlant(day)
        }
    }

    /**
     * Get weather by city
     * @param city where the plants are located.
     */
    fun getWeatherByCity(city: String) {
        weatherRepository.getWeatherByCity(city).enqueue(object : Callback<WeatherItem> {

            override fun onResponse(call: Call<WeatherItem>, response: Response<WeatherItem>) {
                if (response.isSuccessful) weatherItem.value = response.body()
                else error.value = "Error: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<WeatherItem>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}