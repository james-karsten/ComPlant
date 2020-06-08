package com.example.complant.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Get current temperature of a specific Location
 */

@Parcelize
data class WeatherItem (
    @SerializedName("name") val name: String,
    @SerializedName("main") val main: Main
) : Parcelable {

    @Parcelize
    data class Main (
        @SerializedName("temp") val temp: String
    ) : Parcelable
}