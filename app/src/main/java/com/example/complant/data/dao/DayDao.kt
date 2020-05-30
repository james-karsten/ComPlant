package com.example.complant.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.complant.model.Day

@Dao
interface DayDao {

    @Query("SELECT * FROM dayTable")
    fun getDays() : LiveData<List<Day>>

    @Insert
    fun insertDay(day: Day)

    @Delete
    fun deleteDay(day: Day)

    @Update
    fun updateDay(day: Day)

}