package com.abdev.weatherdata.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cityData", primaryKeys = ["cityName"])
data class CityData(

    @NonNull
    @SerializedName("cityName")
    var cityName: String = "",

    @NonNull
    @SerializedName("isSelected")
    var isSelected: Boolean = false

)