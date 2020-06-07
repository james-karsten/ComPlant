package com.example.complant.PlantStatistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.complant.data.repositories.DayRepository
import com.example.complant.data.repositories.PlantRepository
import com.example.complant.model.Day
import com.example.complant.model.PlantWithDays
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlantStatisticsActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val plantRepository = PlantRepository(application.applicationContext)
    private val dayRepository = DayRepository(application.applicationContext)
//    var daysOfPlant: LiveData<List<PlantWithDays>> = plantRepository.getDaysOfPlant()
    var plantsAndDays: LiveData<List<PlantWithDays>> = plantRepository.getPlantsAndDays()

    private val ioScope = CoroutineScope(Dispatchers.IO)

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

//    fun getDaysOfPlant(plantId: Long?) {
//        ioScope.launch {
//            plantRepository.getDaysOfPlant()
//        }
//    }

//    fun insertDaysOfPlant(plantId: Long?, days: Day) {
//        ioScope.launch {
//            plantRepository.insertDaysOfPlant(plantId, days)
//        }
//    }
}