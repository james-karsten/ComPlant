package com.example.complant.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "dayTable")
data class Day (

    @ColumnInfo(name = "dayNumber")
    var dayNumber: Int,

    @ColumnInfo(name = "dayWater")
    var dayWater: Double,

    @ColumnInfo(name = "dayTemperature")
    var dayTemperature: Double,

    @ColumnInfo(name = "daySun")
    var daySun: Double,

    @ColumnInfo(name = "dayLength")
    var dayLength: Double,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dayId")
    val dayId: Long? = null

) : Parcelable