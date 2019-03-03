package com.abdev.weatherdata.data.models

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

    @Transaction
    open fun updateData(cityName: String) {
        updateCities(false)
        updateCity(true, cityName)
    }
}