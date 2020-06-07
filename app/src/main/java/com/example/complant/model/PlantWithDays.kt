package com.example.complant.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

/**
 * Relation between plant and days
 */
data class PlantWithDays (
    @Embedded val plant: Plant,
    @Relation(
        parentColumn = "plantId",
        entityColumn = "dayPlantId"
    )
    val days: List<Day>
)