package com.example.complant.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.complant.data.dao.DayDao
import com.example.complant.data.dao.PlantDao
import com.example.complant.data.database.PlantRoomDatabase
import com.example.complant.model.Day
import com.example.complant.model.Plant

class DayRepository(context: Context) {

    private val dayDao: DayDao

    init {
        val database = PlantRoomDatabase.getDatabase(context)
        dayDao = database!!.dayDao()
    }

    fun getDays() : LiveData<List<Day>> {
        return dayDao.getDays()
    }

    fun deleteDay(day: Day) {
        dayDao.deleteDay(day)
    }

    fun updatePlant(day: Day) {
        dayDao.updateDay(day)
    }

    fun insertDay(day: Day) {
        dayDao.insertDay(day)
    }
}