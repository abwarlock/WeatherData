package com.abdev.weatherdata.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: ArrayList<WeatherData>)

    @Query("SELECT * from weatherdata")
    fun getAllList(): List<WeatherData>?
}