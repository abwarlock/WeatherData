package com.abdev.weatherdata.data.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: ArrayList<CityData>)

    @Query("UPDATE cityData SET isSelected=:isSelected")
    fun updateCities(isSelected: Boolean)

    @Query("UPDATE cityData SET isSelected=:isSelected WHERE cityName =:cityName")
    fun updateCity(isSelected: Boolean, cityName: String)

    @Query("SELECT * FROM cityData WHERE isSelected =:isSelected")
    fun getSelectedCity(isSelected: Boolean): CityData

    @Query("SELECT * FROM cityData")
    fun getAllCities(): List<CityData>

    @Query("SELECT * FROM cityData WHERE isSelected =:isSelected")
    fun getSelectedCityLiveData(isSelected: Boolean): LiveData<CityData>

    @Transaction
    open fun updateData(cityName: String) {
        updateCities(false)
        updateCity(true, cityName)
    }
}