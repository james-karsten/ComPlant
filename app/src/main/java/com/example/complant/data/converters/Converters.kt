package com.example.complant.data.converters

import androidx.room.TypeConverter
import com.example.complant.model.Day
import com.google.gson.Gson
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun listToJson(value: List<Day>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Day>::class.java).toList()
}
