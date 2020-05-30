package com.example.complant.AddPlant

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.complant.data.repositories.PlantRepository
import com.example.complant.model.Plant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPlantActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val plantRepository = PlantRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val success = MutableLiveData<Boolean>()

    fun insertPlant(plant: Plant) {
        ioScope.launch {
            plantRepository.insertPlant(plant)
            Log.i("SUCCES", "${plant.name}")
        }
        success.value = true
    }
}