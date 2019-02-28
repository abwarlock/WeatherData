package com.abdev.weatherdata.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weatherdata")
class WeatherData {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("pId")
    var pId: Int? = null

    @SerializedName("value")
    var value: Double? = 0.toDouble()

    @SerializedName("year")
    var year: Int? = 0

    @SerializedName("month")
    var month: Int? = 0
}
