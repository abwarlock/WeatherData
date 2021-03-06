package com.abdev.weatherdata.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weatherdata", primaryKeys = ["year", "month", "metricType", "cityName"])
class WeatherData {

    @SerializedName("value")
    var value: Double? = 0.toDouble()

    @NonNull
    @SerializedName("year")
    var year: Int? = 0

    @NonNull
    @SerializedName("month")
    var month: Int? = 0

    @NonNull
    @SerializedName("metricType")
    var metricType: Int? = -1

    @NonNull
    @SerializedName("cityName")
    var cityName: String? = ""
}
