package com.example.complant.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.complant.data.dao.DayDao
import com.example.complant.data.dao.PlantDao
import com.example.complant.data.database.PlantRoomDatabase
import com.example.complant.model.Day
import com.example.complant.model.Plant
import com.example.complant.model.PlantWithDays

class PlantRepository(context: Context) {

    private val plantDao: PlantDao

    init {
        val database = PlantRoomDatabase.getDatabase(context)
        plantDao = database!!.plantDao()
    }

    fun getPlants() : LiveData<List<Plant>> {
        return plantDao.getPlants()
    }

    fun deletePlant(plant: Plant) {
        plantDao.deletePlant(plant)
    }

    fun updatePlant(plant: Plant) {
        plantDao.updatePlant(plant)
    }

    fun insertPlant(plant: Plant) {
        plantDao.insertPlant(plant)
    }

    fun getPlantsAndDays() : LiveData<List<PlantWithDays>> {
        return plantDao.getDaysPlants()
    }

//    fun updatePlantsAndDays(plantsAndDays: List<PlantWithDays>) {
//        plantDao.updatePlantsAndDays(plantsAndDays)
//    }
}