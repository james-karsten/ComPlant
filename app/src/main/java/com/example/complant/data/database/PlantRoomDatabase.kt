package com.example.complant.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.complant.data.converters.Converters
import com.example.complant.data.dao.DayDao
import com.example.complant.data.dao.PlantDao
import com.example.complant.model.Day
import com.example.complant.model.Plant


//@Database(entities = [Plant::class, Day::class], version = 1, exportSchema = false)
@Database(entities = [Plant::class, Day::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlantRoomDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao
    abstract fun dayDao(): DayDao

    companion object {
        private const val DATABASE_NAME = "PLANT_DATABASE"

        @Volatile
        private var INSTANCE: PlantRoomDatabase? = null

        fun getDatabase(context: Context) : PlantRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(PlantRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            PlantRoomDatabase::class.java,
                            DATABASE_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE
        }
    }
}