package com.example.complant.PlantComparison

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.complant.data.repositories.PlantRepository
import com.example.complant.model.PlantWithDays
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * Gets all Plants with their days.
 */
class PlantComparisonActivityViewModel (application: Application) : AndroidViewModel(application) {

    private val plantRepository = PlantRepository(application.applicationContext)
    var plantsAndDays: LiveData<List<PlantWithDays>> = plantRepository.getPlantsAndDays()

}