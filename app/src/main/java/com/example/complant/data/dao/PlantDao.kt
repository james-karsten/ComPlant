package com.example.complant.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.complant.model.Plant

@Dao
interface PlantDao {

    @Query("SELECT * FROM plantTable")
    fun getPlants() : LiveData<List<Plant>>

    @Insert
    fun insertPlant(plant: Plant)

    @Delete
    fun deletePlant(plant: Plant)

    @Update
    fun updatePlant(plant: Plant)
}