package com.example.complant.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "plantTable")
data class Plant (

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "length")
    var length: Double,

    @ColumnInfo(name = "kind")
    var kind: String,

    @ColumnInfo(name = "growRate")
    var growRate: Double,

    @ColumnInfo(name = "since")
    var since: Date?,

    @ColumnInfo(name = "useApi")
    var useApi: Boolean,

    @ColumnInfo(name = "city")
    var city: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plantId")
    val plantId: Long? = null

) : Parcelable

