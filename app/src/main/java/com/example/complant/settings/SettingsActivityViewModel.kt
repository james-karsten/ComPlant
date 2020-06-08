package com.example.complant.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.complant.data.repositories.PlantRepository
import com.example.complant.model.Plant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val plantRepository = PlantRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun updatePlant(plant: Plant) {
        ioScope.launch {
            plantRepository.updatePlant(plant)
        }
    }
}