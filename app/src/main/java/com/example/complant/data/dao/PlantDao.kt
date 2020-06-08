package com.example.complant.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.complant.model.Day
import com.example.complant.model.Plant
import com.example.complant.model.PlantWithDays

@Dao
interface PlantDao {

    @Transaction
    @Query("SELECT * FROM plantTable")
    fun getPlants() : LiveData<List<Plant>>

    @Transaction
    @Query("SELECT * FROM plantTable")
    fun getDaysPlants() : LiveData<List<PlantWithDays>>

//    @Update
//    fun updatePlantsAndDays(plantsAndDays: List<PlantWithDays>)

    @Insert
    fun insertPlant(plant: Plant)

    @Delete
    fun deletePlant(plant: Plant)

    @Update
    fun updatePlant(plant: Plant)
}