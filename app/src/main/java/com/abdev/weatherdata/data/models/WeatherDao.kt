package com.abdev.weatherdata.data.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: ArrayList<WeatherData>)

    @Query("SELECT * from weatherdata")
    fun getAllList(): List<WeatherData>?

    @Query("SELECT * from weatherdata")
    fun getLiveDataList(): LiveData<List<WeatherData>>

    @Query("SELECT * from weatherdata WHERE metricType =:metricType")
    fun getLiveDataList(metricType: Int): LiveData<List<WeatherData>>

    /*@Query("SELECT * from weatherdata")
    fun getPageDataList(): DataSource.Factory<WeatherData, Int>*/

}