package com.example.complant.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.complant.data.repositories.PlantRepository
import com.example.complant.model.Plant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val plantRepository = PlantRepository(application.applicationContext)
    var plants: LiveData<List<Plant>> = plantRepository.getPlants()
    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun deleteGame(plant: Plant) {
        ioScope.launch {
            plantRepository.deletePlant(plant)
        }
    }

    fun insertPlant(plant: Plant) {
        ioScope.launch {
            plantRepository.insertPlant(plant)
        }
    }
}