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

    @Insert
    fun insertPlant(plant: Plant)

    @Delete
    fun deletePlant(plant: Plant)

    @Update
    fun updatePlant(plant: Plant)

//    @Transaction
//    @Query("SELECT days FROM plantTable WHERE plantId = :plantIdDays")
//    fun getDaysOfPlant(plantIdDays: Long?) : LiveData<List<Day>>

//    @Query("INSERT INTO plantTable(days) VALUES (:day)")
//    fun insertDaysOfPlant(plantIdDays: Long?, day: Day)
}